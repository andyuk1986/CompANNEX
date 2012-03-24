package com.compannex.exceptions;

/**
 * The runtime exception for CompANNEX.
 * 
 */
public class CompANNEXRuntimeException extends RuntimeException {

	/**
	 * constructor.
	 */
	public CompANNEXRuntimeException() {
		super();
	}

	/**
	 * constructor.
	 * 
	 * @param message
	 *            message for the exception
	 * @param cause
	 *            cause for the exception
	 */
	public CompANNEXRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * constructor.
	 * 
	 * @param message
	 *            message for the exception
	 */
	public CompANNEXRuntimeException(String message) {
		super(message);
	}

	/**
	 * constructor.
	 * 
	 * @param cause
	 *            cause for the exception
	 */
	public CompANNEXRuntimeException(Throwable cause) {
		super(cause);
	}

}
