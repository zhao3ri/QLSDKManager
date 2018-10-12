package com.item.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -5126553715298748578L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException(Throwable t) {
		super(t);
	}

	public ApplicationException(String msg, Throwable t) {
		super(msg, t);
	}
}
