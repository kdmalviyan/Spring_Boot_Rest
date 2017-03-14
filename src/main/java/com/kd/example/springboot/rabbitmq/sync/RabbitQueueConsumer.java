package com.kd.example.springboot.rabbitmq.sync;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitQueueConsumer {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public Object consume(String queueName) {
		return rabbitTemplate.receiveAndConvert(queueName);
	}
}
