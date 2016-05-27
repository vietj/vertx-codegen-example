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

/** @module acme-example-js/irc_message */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JIrcMessage = com.acme.example.IrcMessage;

/**

 @class
*/
var IrcMessage = function(j_val) {

  var j_ircMessage = j_val;
  var that = this;

  /**

   @public

   @return {boolean}
   */
  this.isPrivate = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_ircMessage["isPrivate()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {string}
   */
  this.nick = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_ircMessage["nick()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {string}
   */
  this.chatId = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_ircMessage["chatId()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {string}
   */
  this.message = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_ircMessage["message()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param replyMsg {string} 
   */
  this.reply = function(replyMsg) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'string') {
      j_ircMessage["reply(java.lang.String)"](replyMsg);
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_ircMessage;
};

// We export the Constructor function
module.exports = IrcMessage;