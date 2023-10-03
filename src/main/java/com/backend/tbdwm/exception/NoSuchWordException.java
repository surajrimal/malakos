package com.backend.tbdwm.exception;

public class NoSuchWordException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NoSuchWordException() {
		super();
	}
	public NoSuchWordException(String errors) {
		super(errors);
	}

}
