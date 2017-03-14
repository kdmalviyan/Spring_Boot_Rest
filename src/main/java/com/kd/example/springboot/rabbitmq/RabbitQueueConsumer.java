package com.kd.example.springboot.rabbitmq;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kd.example.springboot.constants.Constants;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service
public class RabbitQueueConsumer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private Channel channel;

	public Object consume(String queueName) throws IOException {
		Set<Object> messages = new TreeSet<Object>();
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				try {
					String message = new String(body, Constants.UTF);
					messages.add(message);
				} finally {
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		boolean autoAck = true;
		channel.basicConsume(queueName, autoAck, consumer);

		// Using spring rabbit template
		rabbitTemplate.receiveAndConvert(queueName);
		return messages;
	}
}
