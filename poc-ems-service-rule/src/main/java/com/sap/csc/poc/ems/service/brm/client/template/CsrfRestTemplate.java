package com.sap.csc.poc.ems.service.brm.client.template;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * @author Vincent Chen
 *
 */
@Component
public final class CsrfRestTemplate extends MightyRestTemplate {

	@PostConstruct
	public final void initial() {
		super.initial();
	}

}
