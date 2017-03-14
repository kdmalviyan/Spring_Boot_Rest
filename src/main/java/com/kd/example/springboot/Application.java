package com.kd.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.kd.example.springboot.constants.Constants;
import com.kd.example.springboot.rabbitmq.InitializeRabbitMQ;

@SpringBootApplication(scanBasePackages = { "com.kd.example" })
public class Application {

	public static void main(String[] args) throws Exception {
		String defaultQueueName = Constants.RABBIT_QUEUE.DEFAULT_QUEUE_NAME;
		 ApplicationContext context = SpringApplication.run(Application.class, args);
		((InitializeRabbitMQ) context.getBean("initializeRabbitMQ")).configureQueue(defaultQueueName);
	}
}