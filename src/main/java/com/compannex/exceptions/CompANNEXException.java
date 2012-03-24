package com.compannex.exceptions;

/**
 * Defined exception for CompANNEX.
 */
public class CompANNEXException extends Exception {

	/**
	 * Default constructor.
	 * 
	 */
	public CompANNEXException() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            message
	 */
	public CompANNEXException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * 
	 * @param cause
	 *            cause for the exception
	 */
	public CompANNEXException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            message
	 * @param cause
	 *            cause for the exception
	 */
	public CompANNEXException(String message, Throwable cause) {
		super(message, cause);
	}

}
