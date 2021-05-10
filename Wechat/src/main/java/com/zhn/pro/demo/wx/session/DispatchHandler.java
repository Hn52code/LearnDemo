package com.zhn.pro.demo.wx.session;

import com.zhn.pro.demo.wx.session.entity.InputMessage;
import com.zhn.pro.demo.wx.session.handle.HandlerMapping;
import com.zhn.pro.demo.wx.session.handle.MessageHandler;

public class DispatchHandler {

    private HandlerMapping handlerMapping;

    public DispatchHandler(HandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    public String doDispatch(InputMessage in) {
        MessageHandler handler = getMessageHandler(in);
        return handler.handleAndReturn(in);
    }

    private MessageHandler getMessageHandler(InputMessage in) {
        String msgType = in.getMsgType();
        if (MessageType.even.name().equals(msgType)) {
            String even = in.getEvent();
            return handlerMapping.getMessageHandler(even);
        }
        return handlerMapping.getMessageHandler(msgType);
    }

    public HandlerMapping getHandlerMapping() {
        return handlerMapping;
    }
}
