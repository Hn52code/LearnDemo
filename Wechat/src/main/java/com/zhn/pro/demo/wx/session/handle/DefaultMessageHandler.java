package com.zhn.pro.demo.wx.session.handle;

import com.zhn.pro.demo.wx.session.entity.InputMessage;

public class DefaultMessageHandler extends MessageHandler {

    @Override
    public String getHandlerName() {
        return DefaultMessageHandler.class.getSimpleName();
    }

    @Override
    public String handleAndReturn(InputMessage in) {
        return "success";
    }

}
