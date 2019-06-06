package com.no8.exception;

/**
 * 自定義的 Exception
 * @author ql
 */
public class BookkeepingException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BookkeepingException() {
		super();
	}
	
	public BookkeepingException(String errMsg, Throwable cause) {
		super(errMsg, cause);
	}
	
	public BookkeepingException(String errMsg) {
		super(errMsg);
	}
	
}
