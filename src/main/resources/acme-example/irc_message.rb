require 'vertx/util/utils.rb'
# Generated from com.acme.example.IrcMessage
module AcmeExample
  #  @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
  class IrcMessage
    # @private
    # @param j_del [::AcmeExample::IrcMessage] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::AcmeExample::IrcMessage] the underlying java delegate
    def j_del
      @j_del
    end
    # @return [true,false]
    def private?
      if !block_given?
        return @j_del.java_method(:isPrivate, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling private?()"
    end
    # @return [String]
    def nick
      if !block_given?
        return @j_del.java_method(:nick, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling nick()"
    end
    # @return [String]
    def chat_id
      if !block_given?
        return @j_del.java_method(:chatId, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling chat_id()"
    end
    # @return [String]
    def message
      if !block_given?
        return @j_del.java_method(:message, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling message()"
    end
    # @param [String] replyMsg 
    # @return [void]
    def reply(replyMsg=nil)
      if replyMsg.class == String && !block_given?
        return @j_del.java_method(:reply, [Java::java.lang.String.java_class]).call(replyMsg)
      end
      raise ArgumentError, "Invalid arguments when calling reply(replyMsg)"
    end
  end
end
