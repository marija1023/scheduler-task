package com.example.scheduler.exception;

public class TaskNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException(String msg) {
		super(msg);
	}

}
