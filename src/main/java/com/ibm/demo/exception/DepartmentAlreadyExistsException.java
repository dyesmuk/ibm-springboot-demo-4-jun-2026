package com.ibm.demo.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1180343167799663840L;

	public DepartmentAlreadyExistsException(String message) {
		super(message);
	}
}