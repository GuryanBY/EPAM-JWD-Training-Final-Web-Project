package com.epam.kgd.victory.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = -8121261312458810670L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}

}
