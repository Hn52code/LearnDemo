package com.zhn.demo.netty.netty2.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientEncoder extends MessageToByteEncoder<byte[]> {

    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] bytes, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(bytes);
    }
}
