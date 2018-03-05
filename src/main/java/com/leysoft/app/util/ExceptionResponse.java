package com.leysoft.app.util;

import java.util.Date;

public class ExceptionResponse {
	
	private String message;
	
	private Date timestamp;
	
	private String details;
	
	private String error;
	
	private String status;

	public ExceptionResponse(String message, Date timestamp, String details) {
		this.message = message;
		this.timestamp = timestamp;
		this.details = details;
	}

	public ExceptionResponse(String message, Date timestamp, String details, String error) {
		this.message = message;
		this.timestamp = timestamp;
		this.details = details;
		this.error = error;
	}

	public ExceptionResponse(String message, Date timestamp, String details, String error, String status) {
		this.message = message;
		this.timestamp = timestamp;
		this.details = details;
		this.error = error;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetails() {
		return details;
	}

	public String getError() {
		return error;
	}

	public String getStatus() {
		return status;
	}
}