package com.zhn.pro.demo.wx.demo.handle;

import com.zhn.pro.demo.wx.common.utils.XmlUtil;
import com.zhn.pro.demo.wx.session.handle.MessageHandler;
import com.zhn.pro.demo.wx.session.entity.InputMessage;
import com.zhn.pro.demo.wx.session.entity.OutputMessage;

public class TextMessageHandler extends MessageHandler {

    @Override
    public String getHandlerName() {
        return TextMessageHandler.class.getSimpleName();
    }

    @Override
    public String handleAndReturn(InputMessage in) {
        OutputMessage out = new OutputMessage();
        out.setToUserName(in.getFromUserName());
        out.setFromUserName(in.getToUserName());
        out.setMsgType(in.getMsgType());
        out.setContent(in.getContent());
        out.setCreateTime((int) System.currentTimeMillis());
        return XmlUtil.toXml(out);
    }
}
