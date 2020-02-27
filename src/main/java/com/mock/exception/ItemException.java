package com.mock.exception;

/**
 * @author vijayan.r
 *
 */
public class ItemException extends Exception {

	// Exception handling with only message
	public ItemException(String message) {
		super("ItemException-" + message);
	}

	// Exception handling with message and Cause
	public ItemException(String message, Throwable cause) {
		super("ItemException-" + message, cause);
	}
}
