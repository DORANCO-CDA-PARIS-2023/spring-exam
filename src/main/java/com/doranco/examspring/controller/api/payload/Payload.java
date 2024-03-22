package com.doranco.examspring.controller.api.payload;


import java.time.LocalDateTime;

public class Payload {

	private String message;
	private Object data;
	private LocalDateTime timestamp;

	public Payload() {
		this.timestamp = LocalDateTime.now();
	}

	public Payload(String message, Object data) {
		this();
		this.message = message;
		this.data = data;
	}

	public Payload(String message) {
		this();
		this.message = message;
	}
	
	public Payload(Object data) {
		this();
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
