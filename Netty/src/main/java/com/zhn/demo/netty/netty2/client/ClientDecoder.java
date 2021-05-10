package com.zhn.demo.netty.netty2.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ClientDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] msg = new byte[7];
        msg[0] = byteBuf.readByte();
        msg[1] = byteBuf.readByte();
        msg[2] = byteBuf.readByte();
        msg[3] = byteBuf.readByte();
        msg[4] = byteBuf.readByte();
        msg[5] = byteBuf.readByte();
        msg[6] = byteBuf.readByte();
        list.add(msg);
    }
}
