package com.zhn.demo.rabbitmq.example.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /**
         * 队列声明改变后需要同时应用到消息生产者和消息消费者身上,若想RabbitMQ不会丢失队列的话，
         * 可以通过下面的方式将其声明为持久化：第二个参数为true,配合下面channel.basicPublish第一个参数的设置.
         */
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        String[] args = {"Shuai Ge", "ai", "MeiNv", "..."};
        String message = getMessage(args);
        /**
         * 我们就能确保RabbitMQ重启后task_queue队列不会丢失。现在需要通过设置
         * MessageProperties属性值为：PERSISTENT_TEXT_PLAIN 将消息标记为持久化.
         */
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
