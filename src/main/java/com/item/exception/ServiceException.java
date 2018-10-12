package com.item.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -5201970937727196384L;

	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable t) {
		super(t);
	}

	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
