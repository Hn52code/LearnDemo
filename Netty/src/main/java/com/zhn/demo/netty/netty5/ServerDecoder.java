package com.zhn.demo.netty.netty5;

import com.zhn.demo.netty.netty3.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ServerDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        Object o = decode(ctx, in);
        if (o != null) {
            list.add(o);
        }
    }

    private Object decode(ChannelHandlerContext ctx, ByteBuf buf) {

        int readableBytes = buf.readableBytes();
        if (readableBytes < 23)
            return null;
        if (readableBytes > 1024) {
            buf.skipBytes(readableBytes);
            ctx.close();
            return null;
        }

        while (true) {
            buf.markReaderIndex();
            if ((buf.readByte() & 0xff) == 0xa8) {
                break;
            }
            if (buf.readerIndex() > 50) {
                ctx.close();
                return null;
            }
        }
        int bodyLength = buf.readUnsignedShort();
        if (bodyLength > buf.readableBytes()) {
            buf.resetReaderIndex();
            return null;
        }

        byte[] data = new byte[bodyLength - 1];
        buf.readBytes(data);

        int check = buf.readUnsignedByte();

        int dataCheck = ByteUtil.byte2Int(data);
        if ((dataCheck & 0xff) == check) {
            return data;
        } else {
            ctx.close();
            return null;
        }
    }
}
