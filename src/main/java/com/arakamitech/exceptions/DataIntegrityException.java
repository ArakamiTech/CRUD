package com.arakamitech.exceptions;

public class DataIntegrityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String mensaje) {
		super(mensaje);
	}

}
