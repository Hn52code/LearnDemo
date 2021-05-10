package com.zhn.demo.netty.netty4;

import com.zhn.demo.netty.netty3.IotProtocolEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class IoTServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    /* 日志层，可sharable，可单例 */
    private LoggingHandler loggingHandler = new LoggingHandler(LogLevel.INFO);

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(loggingHandler);
        //
        pipeline.addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
        // 编解码器
        pipeline.addLast(new IoTServerDecoder());
        pipeline.addLast(new IotProtocolEncoder());
        // 1.消息处理handler 多例模式，主要考虑到 记录心跳的私有变量不可共享，此时采用多例模式。
        // 2.也可使用并发包工具，使下面的处理器改为共享（@sharable）
        pipeline.addLast(new IoTServerMessageHandler());
    }

}
