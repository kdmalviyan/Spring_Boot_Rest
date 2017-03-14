package com.kd.example.springboot.model;

public class Actor {
	public Actor() {
		super();
	}

	public Actor(long id, String username, String email, String fName, String lName) {
		super();
		this.username = username;
		this.email = email;
		this.fName = fName;
		this.lName = lName;
		this.id = id;
	}

	private long id;
	private String username;
	private String email;
	private String fName;
	private String lName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
