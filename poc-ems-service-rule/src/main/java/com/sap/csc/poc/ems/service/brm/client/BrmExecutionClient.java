package com.sap.csc.poc.ems.service.brm.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.util.UriComponentsBuilder;

import com.sap.csc.poc.ems.service.brm.client.template.ConcurrentRestTemplate;
import com.sap.csc.poc.ems.service.brm.config.property.BrmPropertyHolder.AuthenticationConfig.UriConfig.ExecutionUri;

@Component("brmExecutionClient")
public class BrmExecutionClient extends ConcurrentRestTemplate {

	@Autowired
	protected ExecutionUri executionUri;

	public String invoke(String ruleName, String executionBody) {

		ListenableFuture<ResponseEntity<String>> future = getAsyncRestTemplate().exchange(
				UriComponentsBuilder.fromHttpUrl(executionUri.getRoot() + "rules/invoke")
						// Url Variables
						.queryParam("rule_name", ruleName)
						// Build Uri
						.build().toUri(),
				// Method
				HttpMethod.POST,
				// Request entity (must be null)
				null,
				// Response
				String.class);

		try {
			// Waits for the result
			ResponseEntity<String> result = future.get();
			return result.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}

	}
}
