package com.zhn.demo.netty.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 *  init channel class
 *
 * @author zhn <br>
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		// 日志打印
		pipeline.addLast(new LoggingHandler(LogLevel.INFO));
		// 心跳超时设置，读写失联时长,此处3秒间隔读取
		pipeline.addLast(new IdleStateHandler(8, 10, 0, TimeUnit.SECONDS));

//		ByteBuf delimiter = Unpooled.copiedBuffer("\n".getBytes());
//		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(2048,delimiter));
		// 自带字符转码器
//		pipeline.addLast(new StringDecoder());
		pipeline.addLast(new StringEncoder());
		// 将字节码解码转换为封装对象，使用自定义解码器
		pipeline.addLast(new MyDecoder());

		// 自定义的消息处理的handler，其中包括信条处理
		pipeline.addLast(new ServerMsgHandler());

	}

}
