package com.zhn.demo.netty.netty2.server;

import com.zhn.demo.netty.netty2.server.msg.MsgConstant;
import com.zhn.demo.netty.netty2.server.msg.emun.CmdType;
import com.zhn.demo.netty.netty2.server.msg.recei.MsgFactory;
import com.zhn.demo.netty.netty2.server.msg.recei.ReceiveMsg;
import com.zhn.demo.netty.netty3.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyTCPDecoder extends ByteToMessageDecoder {

    private int count;

    private final static Logger logger = LogManager.getLogger(MyTCPDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object o = decode(ctx, in);
        if (o != null) {
            out.add(o);
        }
    }

    private Object decode(ChannelHandlerContext ctx, ByteBuf in) {
        try {
            // 1.读取第一个字节判断是否为其实符，为false时返回null，已读取下个字节判断是否为起始符。
            boolean boolSOI = (in.readByte() & 0xff) == MsgConstant.SOI;
            if (!boolSOI) return null;
            // 获取当前可读的字节长度
            int readLength = in.readableBytes();
            // 2.此处定义读取一个short类型为数据长度值，协议定义两个字节长度
            int bodyLength = in.readUnsignedShort();
            // 判断可读取的数据长度是否大于数据包frame的总体长度。如果不足重新读取。
            if (readLength < bodyLength + 2) {
                in.resetReaderIndex();
                return null;
            } else {
                // 3.解析MAC地址
                // 定义地址数组，接收读取值
                byte[] macByte = new byte[MsgConstant.ADR_OFFSET];
                in.readBytes(macByte);
                String macStr = ByteUtil.byte2Mac2(macByte);
                // 4.解析读取命令
                short command = in.readShort();
                short comValue = (short) ((command >> 8 & 0xff) + (command & 0xff));
                // 5.读取状态
                byte sta = in.readByte();
                // 6.消息长度 = 数据长度 - MAC地址长度（8--变动的）- 命令长度（2） - 状态长度 （1）- 校验位（1）
                int infoLength = bodyLength - MsgConstant.ADR_OFFSET - 4;
                byte[] infoByte = null;
                if (infoLength > 0) {
                    infoByte = new byte[infoLength];
                    in.readBytes(infoByte);
                }
                // 7.读取校验位校验值
                byte eoi = in.readByte();
                byte computeEOI = (byte) (ByteUtil.byte2Int(macByte) + comValue +
                        sta + ByteUtil.byte2Int(infoByte));
                // 8.此处验证消息准确性，如果错误，返回null 同起始符判断一个意思
                if (computeEOI != eoi) {
                    count++;
                    if (count > 3) ctx.channel().close();
                    return null;
                }
                CmdType type = CmdType.getCmdType(comValue);
                // 9.判断消息是否属于规定消息 否则返回 null
                if (null == type) return null;
                ReceiveMsg msg = MsgFactory.createReceiveMsg(type);
                msg.setSendID(macStr);
                msg.setMsgComValue(command);
                msg.setMsgSta(sta);
                msg.setMsgContent(infoByte);
                msg.format();
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
        }
        return null;
    }
}
