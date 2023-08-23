package com.arakamitech.exceptions;

public class UnhautorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnhautorizedException(String mensaje) {
		super(mensaje);
	}
}
