package com.zhn.demo.netty.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {

    private int limit_len = 40;

    private int target = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object o = decode1(ctx, byteBuf);
        if (o != null) {
            list.add(o);
        }
    }

    /* 验证逐个字节读取 */
    private Object decode1(ChannelHandlerContext ctx, ByteBuf byteBuf) {

        System.out.println("ByteBuf容器中的16进制数据：" + ByteBufUtil.hexDump(byteBuf));

        System.out.println("缓冲区容器大小：" + byteBuf.capacity());
        int read1 = byteBuf.readByte();
        System.out.println(byteBuf.readableBytes());
        // String hex = Integer.toHexString(read1);
        // System.out.println("读取的16进制码：" + hex);
        System.out.println("读取的下标index值：" + byteBuf.readerIndex());
//        if ((read1 & 0xff) == 0xA8) {
//            return null;
//        } else {
//            if (byteBuf.readerIndex() > limit_len) {
//                ctx.close();
//            }
            if ((read1 & 0x6D) == 0x6D) {
                target++;
                System.out.println("读取到目标值次数：" + target);

                // 重置下表位置为起始位
                // byteBuf.resetReaderIndex();

                // 清除读写指针
                // byteBuf.clear();

                // 丢弃已读取的数据。
                // byteBuf.discardReadBytes();

                // 跳跃指定字节
                byteBuf.skipBytes(10);

                return target;
            }
            return null;
//        }

    }

}
