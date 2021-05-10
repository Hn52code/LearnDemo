package com.zhn.pro.demo.wx.session.handle;

import com.zhn.pro.demo.wx.session.entity.InputMessage;

public abstract class MessageHandler {

    public abstract String getHandlerName();

    public abstract String handleAndReturn(InputMessage in);

}
