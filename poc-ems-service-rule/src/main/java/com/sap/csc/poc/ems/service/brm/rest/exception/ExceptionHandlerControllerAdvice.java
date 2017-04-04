package com.sap.csc.poc.ems.service.brm.rest.exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sap.csc.poc.ems.service.brm.dto.exception.ErrorCode;

/**
 * @author Vincent Chen
 * 
 */
@ControllerAdvice({ "com.sap.csc.poc.ems.service.brm.rest.alpha" })
public class ExceptionHandlerControllerAdvice {

	private static Logger log = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(HttpServletRequest request, ResourceNotFoundException e) {
		logError(request, e);

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new com.sap.csc.poc.ems.service.brm.dto.exception.Error()
						.setCode(ErrorCode.RESOURCE_NOT_FOUND_ERROR).setMessage(e.getMessage()));
	}

	@ExceptionHandler(ParameterIllegalException.class)
	public ResponseEntity<?> parameterIllegalExceptionHandler(HttpServletRequest request, ParameterIllegalException e) {
		logError(request, e);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new com.sap.csc.poc.ems.service.brm.dto.exception.Error()
						.setCode(ErrorCode.PARAMETER_ILLEGAL_ERROR).setMessage(
								"An invalid value was specified for one of the query parameters in the request URL."));
	}

	@ExceptionHandler(ServerInternalErrorException.class)
	public ResponseEntity<?> serverInternalErrorExceptionHandler(HttpServletRequest request,
			ServerInternalErrorException e) {
		logError(request, e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new com.sap.csc.poc.ems.service.brm.dto.exception.Error()
						.setCode(ErrorCode.RESOURCE_NOT_FOUND_ERROR)
						.setMessage("The server encountered an internal error. Please retry the request."));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(HttpServletRequest request, Exception e) {
		logError(request, e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new com.sap.csc.poc.ems.service.brm.dto.exception.Error().setCode(ErrorCode.SERVER_INTERNAL_ERROR)
						.setMessage("The server met an unexpected error. Please contact administrators."));
	}

	private void logError(HttpServletRequest request, Exception e) {
		log.error("[URI: " + request.getRequestURI() + "]" + "[error: " + e.getMessage() + "]");
	}

}
