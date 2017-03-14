package com.kd.example.springboot.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Order {
	@Null
	private String id;

	@NotNull
	private String ownerName;

	public Order() {
	}

	public Order(String id, String ownerName) {
		super();
		this.id = id;
		this.ownerName = ownerName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
