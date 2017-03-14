package com.kd.example.springboot.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kd.example.springboot.constants.Constants;
import com.rabbitmq.client.Channel;

@Service("initializeRabbitMQ")
public class InitializeRabbitMQ {
	@Autowired
	ConnectionFactory connectionFactory;

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Autowired
	Channel channel;

	public void configureQueue(String queueName) throws Exception {
		if (StringUtils.isEmpty(queueName)) {
			queueName = Constants.RABBIT_QUEUE.DEFAULT_QUEUE_NAME;
		}
		Queue defaultQueue = new Queue(queueName);
		amqpAdmin.declareQueue(defaultQueue);

		// Create queue using rabbit mz
		boolean durable = true;
		channel.queueDeclare(queueName, durable, false, false, null);
	}
}
