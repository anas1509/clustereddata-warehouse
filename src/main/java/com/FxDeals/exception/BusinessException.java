package com.FxDeals.exception;

public class BusinessException extends RuntimeException {

	/*
	 * This class is a custom exception that is used for our custom exceptions
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private int statusCode;
	// we can add other fields depends on the need

	public BusinessException(String stackTrace) {
		super(stackTrace);
	}

	public BusinessException(String code, String stackTrace, int statusCode) {
		super(stackTrace);
		this.code = code;
		this.statusCode = statusCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}