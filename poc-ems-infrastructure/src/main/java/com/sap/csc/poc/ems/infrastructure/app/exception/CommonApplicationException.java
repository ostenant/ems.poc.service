package com.sap.csc.poc.ems.infrastructure.app.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author Diouf Du
 */
public class CommonApplicationException extends RuntimeException {

	private static final long serialVersionUID = -4294736629413760990L;

	private String errorCode;

	private String errorMessage;

	public CommonApplicationException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMessage);
	}

	public CommonApplicationException(String errorCode, String errorMessage, Exception ex) {
		super(ex);
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMessage);
	}

	public String getErrorCode() {
		if (StringUtils.isEmpty(this.errorCode)) {
			String orginalClassName = this.getClass().getSimpleName().replace("Exception", "");
			List<Character> result = new ArrayList<>(orginalClassName.length() + 4);
			int index = 0;
			for (Character character : orginalClassName.toCharArray()) {
				if (index++ == 0) {
					continue;
				}
				else if (Character.isUpperCase(character)) {
					result.add('_');
					result.add(Character.toUpperCase(character));
				}
				else {
					result.add(Character.toUpperCase(character));
				}
			}
			return new String(String.valueOf(result.toArray(new Character[result.size()])));
		}
		return errorCode;

	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
