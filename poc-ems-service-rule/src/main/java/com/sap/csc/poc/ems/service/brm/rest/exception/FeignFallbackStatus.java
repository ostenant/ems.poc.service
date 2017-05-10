package com.sap.csc.poc.ems.service.brm.rest.exception;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;

public class FeignFallbackStatus {
	
	public static final HttpStatus ERROR_CODE= HttpStatus.BAD_REQUEST;
	public static final String ERROR_MESSAGE = "Bad request, then fallback!";
	
	public final static Pair<HttpStatus, String> ERROR_PAIR = Pair.of(ERROR_CODE, ERROR_MESSAGE);
}
