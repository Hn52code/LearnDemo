package com.zhn.pro.demo.wx.session.handle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhouHN
 * @desc 微信会话消息处理器映射器
 * @date 9:55 2019/10/26 0026
 */
public class HandlerMapping {

    private static Logger logger = LogManager.getLogger(HandlerMapping.class);

    private static Map<String, MessageHandler> handlerMappingMap = new ConcurrentHashMap<>();

    private static DefaultMessageHandler defaultMessageHandler = new DefaultMessageHandler();

    protected void addHandler(String type, MessageHandler handler) {
        logger.debug("register a message handler : " + handler.getHandlerName());
        handlerMappingMap.put(type, handler);
    }

    public MessageHandler getMessageHandler(String targetType) {
        MessageHandler messageHandler = handlerMappingMap.get(targetType);
        if (messageHandler != null) return messageHandler;
        return defaultMessageHandler;
    }

    public void printRegisteredHandler() {
        List<String> list = new ArrayList<>();
        list.add(defaultMessageHandler.getHandlerName());
        if (handlerMappingMap.size() > 0) {
            for (Map.Entry<String, MessageHandler> entry : handlerMappingMap.entrySet()) {
                list.add(entry.getValue().getHandlerName());
            }
        }
        logger.debug("registered message handler : " + Arrays.toString(list.toArray()));
    }

    public Map<String, MessageHandler> getHandlerMappingMap() {
        return handlerMappingMap;
    }
}
