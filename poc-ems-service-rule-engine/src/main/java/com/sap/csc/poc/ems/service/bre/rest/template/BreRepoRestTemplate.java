package com.sap.csc.poc.ems.service.bre.rest.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.service.bre.rest.handler.IgnoreResponseErrorHandler;
import com.sap.csc.poc.ems.service.bre.rest.inteceptor.CacheXsrfTokenInterceptor;

@Component
public class BreRepoRestTemplate extends GeneralRestTemplate {

	@Autowired
	protected CacheXsrfTokenInterceptor cacheXsrfTokenInterceptor;

	@Autowired
	protected IgnoreResponseErrorHandler ignoreResponseErrorHandler;

	@Override
	public void initial() {
		super.initial();
		this.getInterceptors().add(cacheXsrfTokenInterceptor);
		this.setErrorHandler(ignoreResponseErrorHandler);
	}
}