package com.zhn.demo.netty.netty3.server;

import com.zhn.demo.netty.netty3.ByteUtil;
import com.zhn.demo.netty.netty3.DeviceConnectManager;
import com.zhn.demo.netty.netty3.msg.*;
import com.zhn.demo.netty.netty3.receive.*;
import com.zhn.demo.netty.netty3.reply.ProtocolAnalysisErrorType;
import com.zhn.demo.netty.netty3.reply.RepliedPacketFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

@Component
public class WrapTransferDataHandler {
    // log日志
    private static final Logger log = LogManager.getLogger(WrapTransferDataHandler.class);
    @Resource
    private MessagePushService pushControl;
    @Resource
    private MessageRpcService rpcControl;

    public void wrap(ChannelHandlerContext ctx, ReceivingDatagram msg) {

        if (Objects.equals(msg.getMac(), DeviceConnectManager.getConfirmRuleDefinedCount2().put(ctx.channel(), msg.getMac()))) {
            log.warn("[服务器数据分发器]：读取第" + DeviceConnectManager.getConfirmRuleDefinedCount2().size()
                    + "个设备的有效消息->>" + msg.toString());
        }
        ProtocolCmd cmd = msg.getCmd();
        switch (cmd) {
            case CMD_PING:
                ping(ctx, msg);
                break;
            case CMD_REGISTER:
                register(ctx, msg);
                break;
            case CMD_WARNING:
                warning(ctx, msg);
                break;
            case CMD_BREAKDOWN:
                breakdown(ctx, msg);
                break;
            case CMD_RESET:
                reset(msg);
                break;
            case CMD_CTRL_SWITCH:
                ctrlSwitch(ctx, msg);
                break;
            case CMD_UPLOAD_REAL_TIME:
                uploadRealTimeData(ctx, msg);
                break;
            case CMD_UPLOAD_PARTS:
                uploadParts(ctx, msg);
                break;
            default:
                other(ctx, msg);
                break;
        }
        msg.getData().release();
    }

    private void other(ChannelHandlerContext ctx, ReceivingDatagram msg) {

    }

    private void ping(ChannelHandlerContext ctx, ReceivingDatagram msg) {
        if (Objects.equals(msg.getMac(), DeviceConnectManager.getConfirmRuleDefinedCount3().put(ctx.channel(), msg.getMac()))) {
            log.warn("[服务器数据分发器]：读取心跳---数量+" + DeviceConnectManager.getConfirmRuleDefinedCount3().size() + "\n" + msg.toString());
        }
    }

    /*上报 -- 包装注册消息*/
    private void register(ChannelHandlerContext ctx, ReceivingDatagram datagram) {
        if (Objects.equals(datagram.getMac(), DeviceConnectManager.getConfirmRuleDefinedCount4().put(ctx.channel(), datagram.getMac()))) {
            log.warn("[服务器数据分发器]：读取登录---数量+" + DeviceConnectManager.getConfirmRuleDefinedCount4().size() + "\n" + datagram.toString());
        }

        ByteBuf buf = datagram.getData();
        if (buf.readableBytes() < 1) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_NO_ENOUGH_LEN));
            log.error("登录可读长度错误---" + datagram.getMac() + "\n" + datagram.toString());
            return;
        }
        String type = IotDeviceModel.getDeviceModel(buf.readByte());
        if (type == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_REGISTER_TYPE));
            log.error("登录可读类型错误---" + datagram.getMac() + "\n" + datagram.toString());
            return;
        }
        String gateway = null;
        if (buf.readableBytes() > 0) {
            log.error("登录可读gateway错误---" + datagram.getMac() + "\n" + datagram.toString());
            byte[] bytes = new byte[ProtocolCons.DEVICE_GATEWAY_LEN];
            buf.readBytes(bytes);
            gateway = ByteUtil.byte2Mac2(bytes);
        }
        // 回复成功
        ctx.writeAndFlush(RepliedPacketFactory.getRegisterReply());
        RegisterMessage message = new RegisterMessage();
        message.setCmd("register");
        message.setMac(datagram.getMac());
        message.setType(type);
        message.setGateway(gateway);
        // 存储连接
        DeviceConnectManager.getConfirmRuleDefinedCount().put(message.getMac(), ctx.channel());
        log.info("[服务端信息-统计]-->>连接数--已定义：" + DeviceConnectManager.getConfirmRuleDefinedCount().size());
        pushControl.register(message);
    }

    /*上报 -- 包装报警消息*/
    private void warning(ChannelHandlerContext ctx, ReceivingDatagram datagram) {
        ByteBuf buf = datagram.getData();
        if (buf.readableBytes() < 1) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_NO_ENOUGH_LEN));
            return;
        }
        int first = buf.readByte();
        String type = DeviceWarn.getWarnName(first);
        if (type == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_WB_TYPE));
            return;
        }
        int second = buf.readUnsignedByte();
        String areaNo = areaNoToStr(second);
        int third = buf.readByte();
        String probe = DeviceProbe.getAreaName(third);
        if (probe == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_WB_PROBE));
            return;
        }
        int forth = buf.readByte();
        String area = DeviceArea.getAreaName(forth);
        if (area == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_WB_AREA));
            return;
        }
        // 回复成功
        ctx.writeAndFlush(RepliedPacketFactory.getWarningReply());
        WarnAndBreakdownMessage message = new WarnAndBreakdownMessage();
        message.setCmd("warn");
        message.setMac(datagram.getMac());
        message.setType(type);
        message.setAreaNo(areaNo);
        message.setAreaType(area);
        message.setProbeType(probe);
        pushControl.warnOrBreakdown(message);
    }

    /*上报 -- 包装故障消息*/
    private void breakdown(ChannelHandlerContext ctx, ReceivingDatagram datagram) {
        ByteBuf buf = datagram.getData();
        if (buf.readableBytes() < 1) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_NO_ENOUGH_LEN));
            return;
        }
        int first = buf.readByte();
        String type = DeviceBreakdown.getBreakdownName(first);
        if (type == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_WB_TYPE));
            return;
        }
        int second = buf.readByte();
        String areaNo = areaNoToStr(second);
        int third = buf.readByte();
        String probe = DeviceProbe.getAreaName(third);
        if (probe == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_WB_PROBE));
            return;
        }
        int forth = buf.readByte();
        String area = DeviceArea.getAreaName(forth);
        if (area == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_WB_AREA));
            return;
        }
        // 回复成功
        ctx.writeAndFlush(RepliedPacketFactory.getBreakdownReply());
        WarnAndBreakdownMessage message = new WarnAndBreakdownMessage();
        message.setCmd("breakdown");
        message.setMac(datagram.getMac());
        message.setType(type);
        message.setAreaNo(areaNo);
        message.setAreaType(area);
        message.setProbeType(probe);
        pushControl.warnOrBreakdown(message);
    }

    /*回复 -- 包装复位消息*/
    private void reset(ReceivingDatagram datagram) {
        ResetMessage message = new ResetMessage();
        message.setCmd("reset");
        message.setMac(datagram.getMac());
        message.setStatus(datagram.getStatus() == ProtocolStatus.STA_SUCCESS ? 0 : 1);
        rpcControl.responseReset(message);
    }

    /*回复 -- 包装控制（开关）消息*/
    private void ctrlSwitch(ChannelHandlerContext ctx, ReceivingDatagram datagram) {
        ByteBuf buf = datagram.getData();
        if (buf.readableBytes() < 1) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_NO_ENOUGH_LEN));
            return;
        }
        int value = buf.readByte();
        ControlSwitchMessage message = new ControlSwitchMessage();
        message.setMac(datagram.getMac());
        message.setCmd("controlswitch");
        message.setStatus(datagram.getStatus() == ProtocolStatus.STA_SUCCESS ? 0 : 1);
        message.setOn_off(value);
        rpcControl.responseControl(message);
    }

    /*上报 -- 包装实时数值消息*/
    private void uploadRealTimeData(ChannelHandlerContext ctx, ReceivingDatagram datagram) {
        if (Objects.equals(datagram.getMac(), DeviceConnectManager.getConfirmRuleDefinedCount5().put(ctx.channel(), datagram.getMac()))) {
            log.warn("[服务器数据分发器]：读取实时数值---数量" + DeviceConnectManager.getConfirmRuleDefinedCount5().size() + "\n" + datagram.toString());
        }
//        ByteBuf buf = datagram.getData();
//        if (buf.readableBytes() < 1) {
//            ctx.writeAndFlush(RepliedPacketFactory.
//                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_NO_ENOUGH_LEN));
//            return;
//        }
//        int value = buf.readByte();
//        // 回复成功
//        ctx.writeAndFlush(RepliedPacketFactory.getPingReply());
//        UploadRealTimeMessage message = new UploadRealTimeMessage();
//        message.setMac(datagram.getMac());
//        message.setCmd("control");
//        message.setOn_off(value);
//        pushControl.uploadRt(message);
    }

    /*上报 -- 包装部件消息*/
    private void uploadParts(ChannelHandlerContext ctx, ReceivingDatagram datagram) {
        ByteBuf buf = datagram.getData();
        if (buf.readableBytes() < 1) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_NO_ENOUGH_LEN));
            return;
        }
        UploadPartsMessage message = new UploadPartsMessage();
        HashMap<String, UploadPartsMessage.Item> parts = new HashMap<>();
        try {
            parts.put("smoke", transfer(buf, message));
            parts.put("gas", transfer(buf, message));
            parts.put("ele", transfer(buf, message));
            parts.put("sos", transfer(buf, message));
        } catch (Exception e) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_DATA_PARTS));
            return;
        }
        // 回复成功
        ctx.writeAndFlush(RepliedPacketFactory.getPartsReply());
        message.setParts(parts);
        message.setCmd("parts");
        message.setMac(datagram.getMac());
        pushControl.uploadParts(message);
    }

    /*转换防区*/
    private String areaNoToStr(int num) {
        if (num != 0xff) {
            if (num < 10)
                return "00" + String.valueOf(num);
            if (num < 100)
                return "0" + String.valueOf(num);
            return String.valueOf(num);
        } else {
            return "-";
        }
    }

    /*读取配件时转换*/
    private UploadPartsMessage.Item transfer(ByteBuf buf, UploadPartsMessage message) throws Exception {
        UploadPartsMessage.Item item = message.new Item();
        item.setTotal(buf.readByte());
        item.setOnline(buf.readByte());
        item.setOutline(buf.readByte());
        return item;
    }


}
