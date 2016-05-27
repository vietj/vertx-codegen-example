package com.acme.example;

import io.vertx.core.Vertx;

import java.util.Random;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class Main {

  public static void main(String[] args) {

    // Generate a random name
    Random r = new Random();
    StringBuilder name = new StringBuilder("bot-");
    for (int i = 0;i < 5;i++) {
      name.append('a' + r.nextInt(26));
    }

    Vertx vertx = Vertx.vertx();

    // Configure the client
    IrcClient client = IrcClient.create(vertx, new IrcOptions().
        setName(name.toString()).
        addChannel("#vertx"));

    // Handle messages
    client.messageHandler(msg -> {
      if (msg.isPrivate()) {
        System.out.println(msg.nick() + " : "  + msg.message());
        msg.reply("Hello " + msg.nick() + " I'm " + name);
      } else {
        System.out.println(msg.chatId() + " / " + msg.nick() + " : "  + msg.message());
      }
    });

    // Connect the bot
    client.connect(ar -> {
      if (ar.succeeded()) {
        System.out.println("Connected");
      } else {
        ar.cause().printStackTrace();
      }
    });

  }
}
