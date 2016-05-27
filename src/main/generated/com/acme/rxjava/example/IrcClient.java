/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.acme.rxjava.example;

import java.util.Map;
import io.vertx.lang.rxjava.InternalHelper;
import rx.Observable;
import io.vertx.rxjava.core.Vertx;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import com.acme.example.IrcOptions;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link com.acme.example.IrcClient original} non RX-ified interface using Vert.x codegen.
 */

public class IrcClient {

  final com.acme.example.IrcClient delegate;

  public IrcClient(com.acme.example.IrcClient delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static IrcClient create(Vertx vertx, IrcOptions options) { 
    IrcClient ret= IrcClient.newInstance(com.acme.example.IrcClient.create((io.vertx.core.Vertx) vertx.getDelegate(), options));
    return ret;
  }

  public IrcClient messageHandler(Handler<IrcMessage> handler) { 
    this.delegate.messageHandler(new Handler<com.acme.example.IrcMessage>() {
      public void handle(com.acme.example.IrcMessage event) {
        handler.handle(new IrcMessage(event));
      }
    });
    return this;
  }

  public IrcClient closeHandler(Handler<Void> handler) { 
    this.delegate.closeHandler(handler);
    return this;
  }

  public void connect(Handler<AsyncResult<IrcClient>> connectHandler) { 
    this.delegate.connect(new Handler<AsyncResult<com.acme.example.IrcClient>>() {
      public void handle(AsyncResult<com.acme.example.IrcClient> event) {
        AsyncResult<IrcClient> f;
        if (event.succeeded()) {
          f = InternalHelper.<IrcClient>result(new IrcClient(event.result()));
        } else {
          f = InternalHelper.<IrcClient>failure(event.cause());
        }
        connectHandler.handle(f);
      }
    });
  }

  public Observable<IrcClient> connectObservable() { 
    io.vertx.rx.java.ObservableFuture<IrcClient> connectHandler = io.vertx.rx.java.RxHelper.observableFuture();
    connect(connectHandler.toHandler());
    return connectHandler;
  }

  public void close() { 
    this.delegate.close();
  }


  public static IrcClient newInstance(com.acme.example.IrcClient arg) {
    return arg != null ? new IrcClient(arg) : null;
  }
}
