package com.zhn.demo.netty.netty3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/* 设备连接管理 */
public class DeviceConnectManager {

    // 总连接数，以注册到服务端客户端为主
    private static AtomicInteger connectCount = new AtomicInteger(0);
    private static AtomicInteger registerTypeErrorCount = new AtomicInteger(0);
    private static AtomicInteger registerGatewayErrorCount = new AtomicInteger(0);
    // 计算符合协议的连接数，符合协议运算规则，但不一定是符合定义的已允许规则
    private static CopyOnWriteArraySet<ChannelHandlerContext> confirmRuleCount = new CopyOnWriteArraySet<>();
    // 计算符合协议的连接数，符合协议运算规则，不符合已允许的规则
    private static CopyOnWriteArraySet<Channel> confirmRuleUndefinedCount = new CopyOnWriteArraySet<>();
    // 计算符合协议的连接数，符合协议运算规则，且符合已允许的规则
    private static ConcurrentHashMap<String, Channel> confirmRuleDefinedCount = new ConcurrentHashMap<>();
    // 计算符合协议的连接数，符合协议运算规则，且符合已允许的规则
    private static ConcurrentHashMap<Channel, String> confirmRuleDefinedCount2 = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Channel, String> confirmRuleDefinedCount3 = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Channel, String> confirmRuleDefinedCount4 = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Channel, String> confirmRuleDefinedCount5 = new ConcurrentHashMap<>();


    public static AtomicInteger getConnectCount() {
        return connectCount;
    }
    public static AtomicInteger getRegisterTypeErrorCount() {
        return registerTypeErrorCount;
    }
    public static AtomicInteger getRegisterGatewayErrorCount() {
        return registerGatewayErrorCount;
    }

    public static CopyOnWriteArraySet<ChannelHandlerContext> getConfirmRuleCount() {
        return confirmRuleCount;
    }

    public static ConcurrentHashMap<String, Channel> getConfirmRuleDefinedCount() {
        return confirmRuleDefinedCount;
    }

    public static ConcurrentHashMap<Channel, String> getConfirmRuleDefinedCount2() {
        return confirmRuleDefinedCount2;
    }

    public static ConcurrentHashMap<Channel, String> getConfirmRuleDefinedCount3() {
        return confirmRuleDefinedCount3;
    }

    public static ConcurrentHashMap<Channel, String> getConfirmRuleDefinedCount4() {
        return confirmRuleDefinedCount4;
    }

    public static ConcurrentHashMap<Channel, String> getConfirmRuleDefinedCount5() {
        return confirmRuleDefinedCount5;
    }

    public static CopyOnWriteArraySet<Channel> getConfirmRuleUndefinedCount() {
        return confirmRuleUndefinedCount;
    }

    public static void removeByValue(Channel value) {
        Collection<Channel> values = confirmRuleDefinedCount.values();
        for (Channel channel : confirmRuleDefinedCount.values()) {
            if (channel == value) {
                values.remove(value);
                return;
            }
        }
    }

    public static String removeDefinedByValueReturnKey(Channel value) {
        for (Map.Entry<String, Channel> entry : confirmRuleDefinedCount.entrySet()) {
            if (entry.getValue() == value) {
                confirmRuleDefinedCount.remove(entry.getKey());
                return entry.getKey();
            }
        }
        return null;
    }

}
