package com.zhn.pro.demo.wx.demo.handle;

import com.zhn.pro.demo.wx.session.entity.InputMessage;
import com.zhn.pro.demo.wx.session.handle.MessageHandler;

public class EvenClickMessageHandler extends MessageHandler {

    @Override
    public String getHandlerName() {
        return EvenClickMessageHandler.class.getSimpleName();
    }

    @Override
    public String handleAndReturn(InputMessage in) {
        return null;
    }
}
