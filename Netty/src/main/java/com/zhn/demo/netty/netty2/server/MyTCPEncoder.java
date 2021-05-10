package com.zhn.demo.netty.netty2.server;

import com.zhn.demo.netty.netty2.server.msg.send.SendMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyTCPEncoder extends MessageToByteEncoder<SendMsg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, SendMsg msg, ByteBuf out) throws Exception {
		out.writeByte(msg.getSoi());
		out.writeShort(msg.getLen());
		out.writeShort(msg.getCom());
		out.writeByte(msg.getSta());
		if (null != msg.getInfo()) {
			out.writeBytes(msg.getInfo());
		}
		out.writeByte(msg.getEoi());
	}

}
