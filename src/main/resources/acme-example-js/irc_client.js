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

/** @module acme-example-js/irc_client */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');
var IrcMessage = require('acme-example-js/irc_message');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JIrcClient = com.acme.example.IrcClient;
var IrcOptions = com.acme.example.IrcOptions;

/**

 @class
*/
var IrcClient = function(j_val) {

  var j_ircClient = j_val;
  var that = this;

  /**

   @public
   @param handler {function} 
   @return {IrcClient}
   */
  this.messageHandler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_ircClient["messageHandler(io.vertx.core.Handler)"](function(jVal) {
      handler(utils.convReturnVertxGen(jVal, IrcMessage));
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   @return {IrcClient}
   */
  this.closeHandler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_ircClient["closeHandler(io.vertx.core.Handler)"](handler);
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param connectHandler {function} 
   */
  this.connect = function(connectHandler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_ircClient["connect(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        connectHandler(utils.convReturnVertxGen(ar.result(), IrcClient), null);
      } else {
        connectHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   */
  this.close = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_ircClient["close()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_ircClient;
};

/**

 @memberof module:acme-example-js/irc_client
 @param vertx {Vertx} 
 @param options {Object} 
 @return {IrcClient}
 */
IrcClient.create = function(vertx, options) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && (typeof __args[1] === 'object' && __args[1] != null)) {
    return utils.convReturnVertxGen(JIrcClient["create(io.vertx.core.Vertx,com.acme.example.IrcOptions)"](vertx._jdel, options != null ? new IrcOptions(new JsonObject(JSON.stringify(options))) : null), IrcClient);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = IrcClient;