package com.sap.csc.poc.ems.service.brm.client.template;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public final class DefaultRestTemplate extends ConcurrentRestTemplate {

	@PostConstruct
	public final void initial() {
		super.initial();
	}

}
