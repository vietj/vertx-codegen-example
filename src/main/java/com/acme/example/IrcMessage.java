package com.acme.example;

import io.vertx.codegen.annotations.VertxGen;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface IrcMessage {

  boolean isPrivate();

  String nick();

  String chatId();

  String message();

  void reply(String replyMsg);

}
