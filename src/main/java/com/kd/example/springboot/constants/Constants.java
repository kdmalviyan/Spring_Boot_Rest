package com.kd.example.springboot.constants;

public interface Constants {

	String UTF = "UTF-8";

	public static interface RABBIT_QUEUE {
		String DEFAULT_QUEUE_NAME = "hello.world.queue";
		String ORDER_QUEUE_NAME = "order.queue";
		String HOST = "localhost";
		String USERNAME = "guest";
		String PASSWORD = "guest";
	}

	public static interface ORDER {
		String ORDER_URI = "/order/{id}";
		String GET_ORDER = "/order/get";
		String CREATE_ORDER = "/order/create";
		String EXCHANGE_NAME = "Order Exchange";
		String EXCHANGE_TYPE = "Order Exchange Type";
	}
}
