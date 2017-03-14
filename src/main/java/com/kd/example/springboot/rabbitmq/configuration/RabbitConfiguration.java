package com.kd.example.springboot.rabbitmq.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kd.example.springboot.constants.Constants;
import com.rabbitmq.client.Channel;

@Configuration
public class RabbitConfiguration {

	private final String defaultQueueName = Constants.RABBIT_QUEUE.DEFAULT_QUEUE_NAME;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(Constants.RABBIT_QUEUE.HOST);
		connectionFactory.setUsername(Constants.RABBIT_QUEUE.USERNAME);
		connectionFactory.setPassword(Constants.RABBIT_QUEUE.PASSWORD);
		return connectionFactory;
	}

	@Bean
	public Channel createChannel() {
		Channel channel = connectionFactory().createConnection().createChannel(true);
		return channel;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		// The routing key is set to the name of the queue by the broker for the
		// default exchange.
		template.setRoutingKey(this.defaultQueueName);
		// Where we will synchronously receive messages from
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		template.setQueue(this.defaultQueueName);
		return template;
	}

	@Bean
	// Every queue is bound to the default direct exchange
	public Queue defaultQueue() {
		return new Queue(this.defaultQueueName);
	}
}