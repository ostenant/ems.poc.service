package com.sap.csc.poc.ems.service.brm.monitor;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.service.brm.rest.exception.ServerInternalErrorException;

/**
 * @author Vincent Chen
 * 
 */
@Aspect
@Component
public class ServiceMonitor {

	private static Logger log = LoggerFactory.getLogger(ServiceMonitor.class);

	@Pointcut("execution(* com.sap.csc.poc.ems.service.brm.client.*.*(..))")
	private void serviceLayer() {
	}

	@AfterThrowing(pointcut = "com.sap.csc.poc.ems.service.brm.monitor.ServiceMonitor.serviceLayer()", throwing = "e")
	public void monitorException(JoinPoint joinPoint, Throwable e) {
		// Log the situation where exception happened
		Object[] args = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		log.error("[" + signature.toShortString() + "]" + Arrays.toString(args) + "[" + e.toString() + "]");

		// Throw a new server internal error exception
		throw new ServerInternalErrorException();
	}

}
