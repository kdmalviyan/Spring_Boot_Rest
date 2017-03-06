package com.kd.example.springboot.controllers;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kd.example.springboot.model.Actor;
import com.kd.example.springboot.model.Payload;

@RestController
public class ActorController {

	private final AtomicInteger counter = new AtomicInteger();

	@RequestMapping("/greeting")
	public Actor greeting(@RequestParam(value = "username", defaultValue = "World") String username) {
		return new Actor(counter.incrementAndGet(), username, username + "@gmail.com", "Kuldeep", "Singh");
	}

	private static final String TEMPLATE = "Hello, %s!";
	
	@RequestMapping("/testPayload")
	public HttpEntity<Payload> preparePayload(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		Payload payload = new Payload(String.format(TEMPLATE, name));
		payload.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ActorController.class).greeting(name)).withSelfRel());
		return new ResponseEntity<Payload>(payload, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<Actor> createUser(@RequestBody Actor actor, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(actor.getId()).toUri());
        return new ResponseEntity<Actor>(actor, headers, HttpStatus.CREATED);
    }
	
}
