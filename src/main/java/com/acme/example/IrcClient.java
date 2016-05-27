package com.acme.example;

import com.acme.example.impl.IrcClientImpl;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface IrcClient {

  static IrcClient create(Vertx vertx, IrcOptions options) {
    return new IrcClientImpl(vertx, options);
  }

  @Fluent
  IrcClient messageHandler(Handler<IrcMessage> handler);

  @Fluent
  IrcClient closeHandler(Handler<Void> handler);

  void connect(Handler<AsyncResult<IrcClient>> connectHandler);

  void close();

}
