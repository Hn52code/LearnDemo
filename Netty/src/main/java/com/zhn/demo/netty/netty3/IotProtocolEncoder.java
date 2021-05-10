package com.zhn.demo.netty.netty3;

import com.zhn.demo.netty.netty3.receive.ProtocolCons;
import com.zhn.demo.netty.netty3.reply.RepliedPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class IotProtocolEncoder extends MessageToByteEncoder<RepliedPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RepliedPacket msg, ByteBuf out) throws Exception {
        out.writeByte(ProtocolCons.SOI);
        out.writeShort(msg.getLength());
        out.writeShort(msg.getCmd());
        out.writeByte(msg.getSta());
        if (msg.getData() != null) {
            // 此处可不用判断空，内部有报错异常
            out.writeBytes(msg.getData());
        }
        out.writeByte(msg.getCheck());
    }
}
