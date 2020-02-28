package com.mock.exception;

public class ItemNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
	}

//	public ItemNotFoundException() {
//		super("No Items to Display");
//		
//	}
//
//	public ItemNotFoundException(String itemName) {
//		super("This item "+itemName+ "not available");
//		
//	}
//
//	public ItemNotFoundException(Long id) {
//		super("This id "+id+"not found");
//		
//	}
	

}
