package com.zhn.demo.netty.netty5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ServerEncoder extends MessageToByteEncoder<byte[]> {

    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] bytes, ByteBuf out) throws Exception {
        if (bytes != null){
            out.writeBytes(bytes);
        }
    }

}
