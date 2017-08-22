package com.epam.kgd.victory.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 3123203880673370108L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
