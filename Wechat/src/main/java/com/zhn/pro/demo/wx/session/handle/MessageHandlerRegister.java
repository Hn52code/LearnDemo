package com.zhn.pro.demo.wx.session.handle;

import com.zhn.pro.demo.wx.session.MessageType;

public abstract class MessageHandlerRegister {

    private HandlerMapping handlerMapping;

    public void setHandlerMapping(HandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    protected void registerHandler(MessageType type, MessageHandler handler) {
        handlerMapping.addHandler(type.name(), handler);
    }

    public abstract void invoke();

}
