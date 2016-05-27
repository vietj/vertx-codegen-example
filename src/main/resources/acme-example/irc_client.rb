require 'vertx/vertx'
require 'acme-example/irc_message'
require 'vertx/util/utils.rb'
# Generated from com.acme.example.IrcClient
module AcmeExample
  #  @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
  class IrcClient
    # @private
    # @param j_del [::AcmeExample::IrcClient] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::AcmeExample::IrcClient] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [Hash] options 
    # @return [::AcmeExample::IrcClient]
    def self.create(vertx=nil,options=nil)
      if vertx.class.method_defined?(:j_del) && options.class == Hash && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComAcmeExample::IrcClient.java_method(:create, [Java::IoVertxCore::Vertx.java_class,Java::ComAcmeExample::IrcOptions.java_class]).call(vertx.j_del,Java::ComAcmeExample::IrcOptions.new(::Vertx::Util::Utils.to_json_object(options))),::AcmeExample::IrcClient)
      end
      raise ArgumentError, "Invalid arguments when calling create(vertx,options)"
    end
    # @yield 
    # @return [self]
    def message_handler
      if block_given?
        @j_del.java_method(:messageHandler, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(::Vertx::Util::Utils.safe_create(event,::AcmeExample::IrcMessage)) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling message_handler()"
    end
    # @yield 
    # @return [self]
    def close_handler
      if block_given?
        @j_del.java_method(:closeHandler, [Java::IoVertxCore::Handler.java_class]).call(Proc.new { yield })
        return self
      end
      raise ArgumentError, "Invalid arguments when calling close_handler()"
    end
    # @yield 
    # @return [void]
    def connect
      if block_given?
        return @j_del.java_method(:connect, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ::Vertx::Util::Utils.safe_create(ar.result,::AcmeExample::IrcClient) : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling connect()"
    end
    # @return [void]
    def close
      if !block_given?
        return @j_del.java_method(:close, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling close()"
    end
  end
end
