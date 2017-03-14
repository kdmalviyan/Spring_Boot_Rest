package com.kd.example.springboot.rabbitmq.sync;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RabbitClient {

	@Autowired
	private AmqpAdmin amqpAdmin;

	public void configureQueue(String queueName) throws Exception {
		String defaultQueueName = "hello.world.queue";
		if (StringUtils.isEmpty(queueName)) {
			queueName = defaultQueueName;
		}
		Queue defaultQueue = new Queue(queueName);
		amqpAdmin.declareQueue(defaultQueue);
	}
}
