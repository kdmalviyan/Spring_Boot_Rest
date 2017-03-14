package com.kd.example.springboot.controllers;

import java.io.IOException;

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

import com.kd.example.springboot.constants.Constants;
import com.kd.example.springboot.model.Order;
import com.kd.example.springboot.rabbitmq.RabbitQueueConsumer;
import com.kd.example.springboot.rabbitmq.RabbitQueueProducer;

@RestController
public class OrderController {

	@Autowired
	RabbitQueueProducer producer;
	@Autowired
	RabbitQueueConsumer consumer;

	@RequestMapping(value = Constants.ORDER.GET_ORDER)
	public Object getUser() throws IOException {
		return consumer.consume(Constants.RABBIT_QUEUE.ORDER_QUEUE_NAME);
	}

	@RequestMapping(value = Constants.ORDER.CREATE_ORDER, method = RequestMethod.POST)
	public ResponseEntity<Order> createUser(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
		order.setId(RandomStringUtils.randomAlphanumeric(24));
		producer.produce(order, Constants.RABBIT_QUEUE.ORDER_QUEUE_NAME);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(Constants.ORDER.ORDER_URI).buildAndExpand(order.getId()).toUri());
		return new ResponseEntity<Order>(order, headers, HttpStatus.CREATED);
	}

}
