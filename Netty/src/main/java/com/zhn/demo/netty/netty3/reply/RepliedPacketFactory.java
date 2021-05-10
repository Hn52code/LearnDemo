package com.zhn.demo.netty.netty3.reply;

import com.zhn.demo.netty.netty3.receive.ProtocolCmd;
import com.zhn.demo.netty.netty3.receive.ProtocolStatus;

public class RepliedPacketFactory {

    public static RepliedPacket getErrorReply(ProtocolAnalysisErrorType type) {
        RepliedPacket error = new RepliedPacket();
        error.setCmd(ProtocolCmd.CMD_ERROR.getKey());
        error.setSta(ProtocolStatus.STA_INVALID.getKey());
        byte[] data = {(byte) type.ordinal()};
        error.setData(data);
        error.wrap();
        return error;
    }

    public static RepliedPacket getPingReply() {
        RepliedPacket ping = new RepliedPacket();
        ping.setCmd(ProtocolCmd.CMD_PING.getKey());
        ping.setSta(ProtocolStatus.STA_NONE.getKey());
        ping.wrap();
        return ping;
    }

    public static RepliedPacket getRegisterReply() {
        RepliedPacket register = new RepliedPacket();
        register.setCmd(ProtocolCmd.CMD_REGISTER.getKey());
        register.setSta(ProtocolStatus.STA_SUCCESS.getKey());
        register.wrap();
        return register;
    }

    public static RepliedPacket getWarningReply() {
        RepliedPacket warn = new RepliedPacket();
        warn.setCmd(ProtocolCmd.CMD_WARNING.getKey());
        warn.setSta(ProtocolStatus.STA_SUCCESS.getKey());
        warn.wrap();
        return warn;
    }

    public static RepliedPacket getBreakdownReply() {
        RepliedPacket breakdown = new RepliedPacket();
        breakdown.setCmd(ProtocolCmd.CMD_BREAKDOWN.getKey());
        breakdown.setSta(ProtocolStatus.STA_SUCCESS.getKey());
        breakdown.wrap();
        return breakdown;
    }

    public static RepliedPacket getResetReply() {
        RepliedPacket reset = new RepliedPacket();
        reset.setCmd(ProtocolCmd.CMD_RESET.getKey());
        reset.setSta(ProtocolStatus.STA_NONE.getKey());
        reset.wrap();
        return reset;
    }

    /* value值 0关闭1开启 */
    public static RepliedPacket getControlSwitchReply(int value) {
        RepliedPacket ctrl = new RepliedPacket();
        ctrl.setCmd(ProtocolCmd.CMD_CTRL_SWITCH.getKey());
        ctrl.setSta(ProtocolStatus.STA_NONE.getKey());
        byte[] data = {(byte)value};
        ctrl.setData(data);
        ctrl.wrap();
        return ctrl;
    }

    public static RepliedPacket getPartsReply() {
        RepliedPacket parts = new RepliedPacket();
        parts.setCmd(ProtocolCmd.CMD_UPLOAD_PARTS.getKey());
        parts.setSta(ProtocolStatus.STA_SUCCESS.getKey());
        parts.wrap();
        return parts;
    }


}
