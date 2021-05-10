package com.zhn.demo.rabbitmq.example.sub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class EmitLog {

    /**
     * RabbitMQ消息模型的核心理念是生产者永远不会直接发送任何消息给队列，一般的情况生产者甚至不知道消息应该发送到哪些队列。
     * 相反的，生产者只能发送消息给交换机（Exchange）。交换机的作用非常简单，一边接收从生产者发来的消息，另一边把消息推送到队列中。
     * 交换机必须清楚的知道消息如何处理它收到的每一条消息。是否应该追加到一个指定的队列？是否应该追加到多个队列？或者是否应该丢弃？
     * 这些规则通过交换机的类型进行定义。 交换机的类型有：direct，topic，headers 和
     * fanout。我们以fanout为例创建一个“logs”类型的交换机。
     */
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 设置交换机，以及类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String[] sendMsgs = {"I", "saw", "a", "dog"};
        String message = getMessage(sendMsgs);

        // 推送消息
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));

        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "info: Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0)
            return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
