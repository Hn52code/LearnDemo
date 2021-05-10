package com.zhn.demo.rabbitmq.example.sub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReceiverLogsTopic {

	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();

		System.out.println(queueName);

		String[] bindingKeys = { "*.orange.*", "*.*.rabbit", "lazy.#" };
		for (final String bindingKey : bindingKeys) {
			channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, StandardCharsets.UTF_8);
					System.out.println("[" + bindingKey + "] Received message :'" + message + "' from routingKey : "
							+ envelope.getRoutingKey());
				}
			};
			channel.basicConsume(queueName, true, consumer);
		}

	}
}
