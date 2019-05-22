package com.cts.favouritelistservice.exception;

@SuppressWarnings("serial")
public class PoemNotFoundException extends Exception {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PoemNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "PoemNotFoundException [message=" + message + "]";
	}


}



	