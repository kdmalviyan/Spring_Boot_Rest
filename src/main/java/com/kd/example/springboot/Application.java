package com.kd.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.kd.example.springboot.rabbitmq.sync.RabbitClient;

@SpringBootApplication(scanBasePackages = { "com.kd.example" })
public class Application {

	public static void main(String[] args) throws Exception {
		String defaultQueueName = "hello.world.queue";
		 ApplicationContext context = SpringApplication.run(Application.class, args);
		((RabbitClient) context.getBean("rabbitClient")).configureQueue(defaultQueueName);
	}
}