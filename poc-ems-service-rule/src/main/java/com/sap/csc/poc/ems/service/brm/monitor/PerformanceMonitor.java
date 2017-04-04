package com.sap.csc.poc.ems.service.brm.monitor;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Chen
 * 
 */
@Aspect
@Component
public class PerformanceMonitor {

	private static Logger log = LoggerFactory.getLogger(PerformanceMonitor.class);

	@Pointcut("execution(public * com.sap.csc.poc.ems.service.brm.rest..*.*(..))")
	private void controllerLayer() {
	}

	@Around("controllerLayer()")
	public Object monitorElapsedTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Timing the method in controller layer
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();

		// Log the elapsed time
		double elapsedTime = stopWatch.getTime() / 1000.0;
		Signature signature = proceedingJoinPoint.getSignature();
		String infoString = "[" + signature.toShortString() + "][Elapsed time: " + elapsedTime + " s]";
		if (elapsedTime > 5) {
			log.error(infoString + "[Note that it's time consuming!]");
		} else {
			log.info(infoString);
		}

		// Return the result
		return result;
	}

}
