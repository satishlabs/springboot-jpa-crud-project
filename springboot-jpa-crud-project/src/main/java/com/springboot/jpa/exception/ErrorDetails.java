package com.springboot.jpa.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	
	public ErrorDetails() {}

	public ErrorDetails(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	
	
}
