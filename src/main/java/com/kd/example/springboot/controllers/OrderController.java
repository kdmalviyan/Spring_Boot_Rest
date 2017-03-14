package com.kd.example.springboot.controllers;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kd.example.springboot.model.Order;
import com.kd.example.springboot.rabbitmq.sync.RabbitQueueConsumer;
import com.kd.example.springboot.rabbitmq.sync.RabbitQueueProducer;

@RestController
public class OrderController {

	@Autowired
	RabbitQueueProducer producer;
	@Autowired
	RabbitQueueConsumer consumer;

	@RequestMapping(value = "/order/get")
	public Object getUser() {
		return consumer.consume("hello.world.queue");
	}

	@RequestMapping(value = "/order/create", method = RequestMethod.POST)
	public ResponseEntity<Order> createUser(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
		order.setId(RandomStringUtils.randomAlphanumeric(24));
		producer.produce(order, "hello.world.queue");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri());
		return new ResponseEntity<Order>(order, headers, HttpStatus.CREATED);
	}

}
