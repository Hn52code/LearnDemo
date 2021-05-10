package com.zhn.demo.redis.pubsub;

import redis.clients.jedis.JedisPubSub;

public class RedisMsgListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("收到消息成功！ channel： %s, message： %s", channel, message));
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("订阅频道成功！ channel： %s, subscribedChannels %d",
                channel, subscribedChannels));

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("取消订阅频道！ channel： %s, subscribedChannels： %d",
                channel, subscribedChannels));
    }

}
