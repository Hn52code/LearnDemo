package com.zhn.demo.rabbitmq.example.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("messageProducer")
public class MessageProducer {
	/*
	 * @Resource(name="amqpTemplate") private AmqpTemplate amqpTemplate;
	 */

	@Resource
	private AmqpTemplate amqpTemplate;

	public void sendMessage(Object message) {
		// amqpTemplate.convertAndSend("queueTestKey", message);
		// amqpTemplate.convertAndSend("queueTestZhou", message);
		amqpTemplate.convertAndSend("pads.QQQ.QQ", message);

	}

}
