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

package com.acme.groovy.example;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.groovy.core.Vertx
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import com.acme.example.IrcOptions
/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
*/
@CompileStatic
public class IrcClient {
  private final def com.acme.example.IrcClient delegate;
  public IrcClient(Object delegate) {
    this.delegate = (com.acme.example.IrcClient) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static IrcClient create(Vertx vertx, Map<String, Object> options) {
    def ret= InternalHelper.safeCreate(com.acme.example.IrcClient.create((io.vertx.core.Vertx)vertx.getDelegate(), options != null ? new com.acme.example.IrcOptions(new io.vertx.core.json.JsonObject(options)) : null), com.acme.groovy.example.IrcClient.class);
    return ret;
  }
  public IrcClient messageHandler(Handler<IrcMessage> handler) {
    this.delegate.messageHandler(new Handler<com.acme.example.IrcMessage>() {
      public void handle(com.acme.example.IrcMessage event) {
        handler.handle(new com.acme.groovy.example.IrcMessage(event));
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
        AsyncResult<IrcClient> f
        if (event.succeeded()) {
          f = InternalHelper.<IrcClient>result(new IrcClient(event.result()))
        } else {
          f = InternalHelper.<IrcClient>failure(event.cause())
        }
        connectHandler.handle(f)
      }
    });
  }
  public void close() {
    this.delegate.close();
  }
}
