package com.fan.core;

public class BaseBussException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseBussException() {
		super();
	}
	public BaseBussException(String message){
		super(message);
	}
}
