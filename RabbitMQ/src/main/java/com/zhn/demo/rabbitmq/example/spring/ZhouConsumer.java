package com.zhn.demo.rabbitmq.example.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class ZhouConsumer implements MessageListener{

	@Override
	public void onMessage(Message message) {
		System.out.println("Zhou..."+message);
	}
}
