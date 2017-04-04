package com.sap.csc.poc.ems.service.brm.dto.exception;

import java.io.Serializable;

/**
 * @author Vincent Chen
 * 
 */
public class Error implements Serializable {

	private static final long serialVersionUID = 7660756960387438399L;

	private int code; // Error code
	private String message; // Error message

	public int getCode() {
		return code;
	}

	public Error setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Error setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		return "Error [code=" + code + ", message=" + message + "]";
	}

}
