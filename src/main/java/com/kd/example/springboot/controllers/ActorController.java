package com.kd.example.springboot.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kd.example.springboot.model.Actor;
import com.kd.example.springboot.rabbitmq.RabbitQueueConsumer;
import com.kd.example.springboot.rabbitmq.RabbitQueueProducer;

@RestController
public class ActorController {

	@Autowired
	RabbitQueueProducer producer;
	@Autowired
	RabbitQueueConsumer consumer;

	@RequestMapping("/user/get/{id}")

	public Object getUser(@PathVariable("id") long id) throws IOException {
		Actor actor = new Actor(id, "kdmalviyan", "kdmalviyan@gmail.com", "Kuldeep", "Singh");
		producer.produce(actor, "hello.world.queue");
		return consumer.consume("hello.world.queue");
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public ResponseEntity<Actor> createUser(@RequestBody Actor actor, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(actor.getId()).toUri());
		return new ResponseEntity<Actor>(actor, headers, HttpStatus.CREATED);
	}

}
