package com.zhn.pro.demo.wx.session;

import com.zhn.pro.demo.wx.session.handle.HandlerMapping;
import com.zhn.pro.demo.wx.session.handle.MessageHandlerRegister;

public class SessionModel {

    private DispatchHandler dispatchHandler;
    private MessageHandlerRegister handlerRegister;

    public SessionModel() {
        this.dispatchHandler = new DispatchHandler(new HandlerMapping());
    }

    public DispatchHandler getDispatchHandler() {
        return dispatchHandler;
    }

    public MessageHandlerRegister getMessageHandlerRegister() {
        return handlerRegister;
    }

    public void setMessageHandlerRegister(MessageHandlerRegister handlerRegister) {
        this.handlerRegister = handlerRegister;
        this.handlerRegister.setHandlerMapping(dispatchHandler.getHandlerMapping());
        this.handlerRegister.invoke();
    }
}
