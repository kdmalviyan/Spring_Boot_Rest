package com.kd.example.springboot.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitQueueProducer {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void produce(Object message, String routingKey) {
		rabbitTemplate.convertAndSend(routingKey, message);
	}
}
