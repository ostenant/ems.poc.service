package com.sap.csc.poc.ems.service.bre.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.sap.csc.poc.ems.service.bre.BreConfig.AuthenticationConfig.UriConfig.InvokerUri;
import com.sap.csc.poc.ems.service.bre.rest.template.BreRepoRestTemplate;

@Component
public class BreInvokerComponent {

	@Autowired
	protected InvokerUri invokerUri;

	@Autowired
	protected BreRepoRestTemplate breRepoRestTemplate;

	public String invoke(String ruleName, String executionBody) {
		return breRepoRestTemplate.exchange(
				new RequestEntity<String>(
						// Body
						executionBody,
						// Method
						HttpMethod.POST,
						// Uri
						UriComponentsBuilder.fromHttpUrl(invokerUri.getRoot() + "rules/invoke")
								// Url Variables
								.queryParam("rule_name", ruleName)
								// Build
								.build().toUri()),
				// Response
				String.class).getBody();
	}
}
