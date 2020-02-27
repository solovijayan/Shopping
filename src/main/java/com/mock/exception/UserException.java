package com.mock.exception;

/**
 * @author vijayan.r
 *
 */
public class UserException extends Exception {

	// Exception handling with only message
	public UserException(String message) {
		super("UserException-" + message);
	}

	// Exception handling with message and cause
	public UserException(String message, Throwable cause) {
		super("UserException-" + message, cause);
	}
}