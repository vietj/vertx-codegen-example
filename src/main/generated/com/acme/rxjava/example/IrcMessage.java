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

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link com.acme.example.IrcMessage original} non RX-ified interface using Vert.x codegen.
 */

public class IrcMessage {

  final com.acme.example.IrcMessage delegate;

  public IrcMessage(com.acme.example.IrcMessage delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public boolean isPrivate() { 
    boolean ret = this.delegate.isPrivate();
    return ret;
  }

  public String nick() { 
    String ret = this.delegate.nick();
    return ret;
  }

  public String chatId() { 
    String ret = this.delegate.chatId();
    return ret;
  }

  public String message() { 
    String ret = this.delegate.message();
    return ret;
  }

  public void reply(String replyMsg) { 
    this.delegate.reply(replyMsg);
  }


  public static IrcMessage newInstance(com.acme.example.IrcMessage arg) {
    return arg != null ? new IrcMessage(arg) : null;
  }
}
