package com.acme.example.impl;

import com.acme.example.IrcClient;
import com.acme.example.IrcMessage;
import com.acme.example.IrcOptions;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericUserEvent;
import org.pircbotx.output.OutputChannel;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class IrcClientImpl implements IrcClient {

  private final Vertx vertx;
  private final IrcOptions options;
  private Handler<Void> closeHandler;
  private Handler<IrcMessage> messageHandler;
  private Configuration.Builder<PircBotX> config;
  private Adapter adapter;

  public IrcClientImpl(Vertx vertx, IrcOptions options) {

    config = new Configuration.Builder<>().
        setName(options.getName()).
        setNickservPassword(options.getNickServPassword()).
        setSocketTimeout(options.getSocketTimeout()).
        setServerHostname(options.getHost()).
        setServerPort(options.getPort()).
        setAutoNickChange(true).
        setAutoReconnect(false).
        setSocketFactory(SOCKET_FACTORY);
    for (String channel : options.getChannels()) {
      config = config.addAutoJoinChannel(channel);
    }

    this.vertx = vertx;
    this.options = options;
  }

  @Override
  public synchronized IrcClient messageHandler(Handler<IrcMessage> handler) {
    messageHandler = handler;
    return this;
  }

  @Override
  public synchronized IrcClient closeHandler(Handler<Void> handler) {
    closeHandler = handler;
    return this;
  }

  @Override
  public synchronized void connect(Handler<AsyncResult<IrcClient>> connectHandler) {
    if (adapter != null) {
      throw new IllegalStateException("Connected");
    }
    Context context = vertx.getOrCreateContext();
    Future<IrcClient> future = Future.<IrcClient>future().setHandler(connectHandler);
    adapter = new Adapter(config, context, messageHandler, closeHandler, future);
    adapter.thread.start();
  }

  @Override
  public synchronized void close() {
    if (adapter != null && !adapter.thread.isInterrupted()) {
      adapter.thread.interrupt();
    }
  }

  class Adapter extends ListenerAdapter<PircBotX> implements Runnable {

    final Context context;
    final Future<IrcClient> completion;
    final PircBotX bot;
    final Thread thread;
    final Handler<IrcMessage> messageHandler;
    final Handler<Void> closeHandler;

    public Adapter(
        Configuration.Builder<PircBotX> config,
        Context context,
        Handler<IrcMessage> messageHandler,
        Handler<Void> closeHandler,
        Future<IrcClient> completion) {

      config.addListener(this);

      this.messageHandler = messageHandler != null ? messageHandler : msg -> {};
      this.closeHandler = closeHandler != null ? closeHandler : v -> {};
      this.context = context;
      this.completion = completion;
      this.bot = new PircBotX(config.buildConfiguration());
      this.thread = new Thread(this);
    }

    @Override
    public void onConnect(ConnectEvent<PircBotX> event) throws Exception {
      context.runOnContext(v -> {
        completion.complete();
      });
    }

    @Override
    public void onDisconnect(DisconnectEvent<PircBotX> event) throws Exception {
      handleClose();
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event) throws Exception {
      processMessage(event, false, event.getChannel().getName(), event.getMessage());
    }

    @Override
    public void onPrivateMessage(PrivateMessageEvent<PircBotX> event) throws Exception {
      processMessage(event, true, null, event.getMessage());
    }

    private void processMessage(GenericUserEvent<PircBotX> event, boolean isPrivate, String chatId, String msg) {
      context.runOnContext(v -> {
        messageHandler.handle(new IrcMessage() {
          @Override
          public boolean isPrivate() {
            return isPrivate;
          }
          @Override
          public String nick() {
            return event.getUser().getNick();
          }
          @Override
          public String chatId() {
            return chatId;
          }
          @Override
          public String message() {
            return msg;
          }
          @Override
          public void reply(String replyMsg) {
            context.executeBlocking(fut -> {
              String[] lines = replyMsg.split("\\r?\\n");
              for (String line : lines) {
                event.respond(line);
              }
            }, res -> {});
          }
        });
      });
    }

    private void handleClose() {
      synchronized (IrcClientImpl.this) {
        adapter = null;
      }
      context.runOnContext(v -> {
        closeHandler.handle(null);
      });
    }

    @Override
    public void run() {
      try {
        bot.startBot();
      } catch (Exception e) {
        if (!completion.isComplete()) {
          synchronized (IrcClientImpl.this) {
            adapter = null;
          }
          completion.fail(e);
        } else {
          handleClose();
        }
      }
    }
  }

  private static final SocketFactory SOCKET_FACTORY = new SocketFactory() {

    final SocketFactory def = SocketFactory.getDefault();

    @Override
    public Socket createSocket(String host, int port) throws IOException {
      return def.createSocket(host, port);
    }

    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
      if (localHost == null && localPort == 0) {
        return def.createSocket(host, port);
      } else {
        return def.createSocket(host, port, localHost, localPort);
      }
    }

    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
      return def.createSocket(host, port);
    }

    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
      if (localAddress == null && localPort == 0) {
        return def.createSocket(address, port);
      } else {
        return def.createSocket(address, port, localAddress, localPort);
      }
    }
  };
}
