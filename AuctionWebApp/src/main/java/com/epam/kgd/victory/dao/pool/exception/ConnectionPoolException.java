package com.epam.kgd.victory.dao.pool.exception;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = -1926412654286627507L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

}
