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
/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
*/
@CompileStatic
public class IrcMessage {
  private final def com.acme.example.IrcMessage delegate;
  public IrcMessage(Object delegate) {
    this.delegate = (com.acme.example.IrcMessage) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public boolean isPrivate() {
    def ret = this.delegate.isPrivate();
    return ret;
  }
  public String nick() {
    def ret = this.delegate.nick();
    return ret;
  }
  public String chatId() {
    def ret = this.delegate.chatId();
    return ret;
  }
  public String message() {
    def ret = this.delegate.message();
    return ret;
  }
  public void reply(String replyMsg) {
    this.delegate.reply(replyMsg);
  }
}
