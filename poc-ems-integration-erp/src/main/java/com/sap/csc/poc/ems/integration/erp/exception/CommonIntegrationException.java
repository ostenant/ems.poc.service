package com.sap.csc.poc.ems.integration.erp.exception;

import com.sap.csc.poc.ems.infrastructure.app.exception.CommonApplicationException;

/**
 * @author I071053
 */
public class CommonIntegrationException extends CommonApplicationException {

	private static final long serialVersionUID = -2854497459395419345L;

	public CommonIntegrationException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public CommonIntegrationException(String errorCode, String errorMessage, Exception ex) {
		super(errorCode, errorMessage, ex);
	}
}
