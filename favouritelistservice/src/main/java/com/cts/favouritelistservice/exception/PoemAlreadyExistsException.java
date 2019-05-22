package com.cts.favouritelistservice.exception;

@SuppressWarnings("serial")
public class PoemAlreadyExistsException extends Exception{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PoemAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "PoemAlreadyExistsException [message=" + message + "]";
	}
	
}




	
