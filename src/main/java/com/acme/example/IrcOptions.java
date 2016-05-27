package com.acme.example;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject
public class IrcOptions {

  private static final String DEFAULT_HOST = "irc.freenode.net";
  private static final int DEFAULT_PORT = 6667;
  private static final String DEFAULT_NAME = null;
  private static final String DEFAULT_NICKSERV_PASSWORD = null;
  private static final int DEFAULT_SOCKET_TIMEOUT = 10000;

  private String host;
  private int port;
  private List<String> channels;
  private String name;
  private String nickServPassword;
  private int socketTimeout;

  public IrcOptions() {
    host = DEFAULT_HOST;
    port = DEFAULT_PORT;
    channels = new ArrayList<>();
    name = DEFAULT_NAME;
    nickServPassword = DEFAULT_NICKSERV_PASSWORD;
    socketTimeout = DEFAULT_SOCKET_TIMEOUT;
  }

  public IrcOptions(JsonObject json) {
    throw new UnsupportedOperationException();
  }

  public IrcOptions(IrcOptions that) {
    host = that.host;
    port = that.port;
    channels = new ArrayList<>(that.channels);
    name = that.name;
    nickServPassword = that.nickServPassword;
    socketTimeout = that.socketTimeout;
  }

  public String getHost() {
    return host;
  }

  public IrcOptions setHost(String host) {
    this.host = host;
    return this;
  }

  public int getPort() {
    return port;
  }

  public IrcOptions setPort(int port) {
    this.port = port;
    return this;
  }

  public List<String> getChannels() {
    return channels;
  }

  public IrcOptions addChannel(String channel) {
    channels.add(channel);
    return this;
  }

  public String getName() {
    return name;
  }

  public IrcOptions setName(String name) {
    this.name = name;
    return this;
  }

  public String getNickServPassword() {
    return nickServPassword;
  }

  public IrcOptions setNickServPassword(String nickServPassword) {
    this.nickServPassword = nickServPassword;
    return this;
  }

  public int getSocketTimeout() {
    return socketTimeout;
  }

  public IrcOptions setSocketTimeout(int socketTimeout) {
    this.socketTimeout = socketTimeout;
    return this;
  }
}