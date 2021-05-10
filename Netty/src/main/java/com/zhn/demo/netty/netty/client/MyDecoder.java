package com.zhn.demo.netty.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object o = decode(channelHandlerContext, byteBuf);
        if (o != null) list.add(o);
    }

    private Object decode(ChannelHandlerContext ctx, ByteBuf in) {
        boolean isSOI = (in.readByte() & 0xff) == 0xA8;
        if (!isSOI)
            return null;
        in.markReaderIndex();
        int readableLen = in.readableBytes();
        int len = in.readShort();
        if (readableLen < len) {
            in.resetReaderIndex();
            return null;
        }
        short com = in.readShort();

        int sta = in.readByte();

        int eoi = in.readByte();

        int compute = com & 0xff + (com >> 8) & 0xff + sta & 0xff;

        if (compute != eoi) ;
        return null;
    }
}
