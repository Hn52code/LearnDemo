package com.zhn.demo.netty.netty3;

import com.zhn.demo.netty.netty3.receive.ProtocolCons;
import com.zhn.demo.netty.netty3.reply.ProtocolAnalysisErrorType;
import com.zhn.demo.netty.netty3.reply.RepliedPacketFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class IotProtocolDecoder extends ByteToMessageDecoder {

    private static final Logger log = LogManager.getLogger(IotProtocolDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object o = decode(ctx, in);
        if (o != null) {
            out.add(o);
        }
    }

    /*
     *  此处解码重点在于区分截取到的信息是否为协议所定义的消息。
     *  1.读取标识符；判断
     *  2.读取长度位；判断
     *  3.读取数据位；计算，不解析，将具体数据交给处理器去分析。
     *  4.读取验证位；比较验证。
     * */
    private Object decode(ChannelHandlerContext ctx, ByteBuf in) {

        log.warn("消息解码：" + ByteBufUtil.hexDump(in));
        String all = ByteBufUtil.hexDump(in);

        // 可读长度小于数据包最小长度返回空。
        if (in.readableBytes() < ProtocolCons.MIN_PACKAGE_LEN)
            return null;

        // 防止码流，并切断连接
        if (in.readableBytes() > 1024) {
            in.skipBytes(in.readableBytes());
            ctx.close();
            log.error("切断-----：码流");
            return null;
        }
        // 记录包头开始的index
        while (true) {
            // System.out.println("index：" + in.readerIndex());
            // 标记起始位的下标位。此方法用于标记下标位置，
            // 当想是readerIndex回到该位置时，调用resetReaderIndex
            in.markReaderIndex();
            // 读到起始标志位结束循环
            if ((in.readByte() & 0xff) == ProtocolCons.SOI) {
                break;
            }
            // 如果读取的下标超过协议最大长度的，则跳跃可读字符，并关闭连接。
            if (in.readerIndex() > ProtocolCons.MAX_INDEX) {
                in.skipBytes(in.readableBytes());
                ctx.channel().writeAndFlush(RepliedPacketFactory.
                        getErrorReply(ProtocolAnalysisErrorType.ERROR_OVER_LIMIT_NO_SIGN));
                ctx.close();
                log.error("切断-----：可读取下标位大于最大的下标位");
                return null;
            }
            // 在没满足上述的条件下,并且可读位小于基本长度则返回。
            if (in.readableBytes() < ProtocolCons.MIN_PACKAGE_LEN) {
                return null;
            }
        }
        // 在获取到标识位后，读取判断，如果可读长度小于数据包中指定长度，则返回并还原起始位置。
        int bodyLen = in.readUnsignedShort();
//        log.info("body-len：" + bodyLen);

        if (bodyLen > ProtocolCons.MAX_DATA_LEN) {
            in.skipBytes(in.readableBytes());
            ctx.channel().writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_LEN));
            ctx.close();
            log.error("切断-----：可读取长度大于实际定义的最大长度");
            return null;
        }
        if (in.readableBytes() < bodyLen) {
            // 重置readerIndex，回到被标识的下标位。
            in.resetReaderIndex();
//            log.info("看可读位不足长度位");
            log.error("切断-----：可读取长度小于，数据包内部传递长度");
            return null;
        }
        // 在长度允许下，读取数据位。
        byte[] data = new byte[bodyLen - 1];
        in.readBytes(data);
//        ByteBuf data = Unpooled.buffer(bodyLen - 1);
//        in.readBytes(data);
//        in.skipBytes(bodyLen - 1);

        // 读取验证位，计算data的验证值
        // 对比数据包验证位，相同则解码成功；不同则切断连接
        int check = in.readUnsignedByte();
        int dataCheck = ByteUtil.byte2Int(data);
        if (check == (dataCheck & 0xff)) {
            return data;
        } else {
            in.skipBytes(in.readableBytes());
//            in.release();
            ctx.channel().writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_CHECK));
            log.error("切断-----解码数据：" + all);
            log.error("切断-----：校验位计算不符合实际值");
            ctx.close();
            return null;
        }
    }

}
