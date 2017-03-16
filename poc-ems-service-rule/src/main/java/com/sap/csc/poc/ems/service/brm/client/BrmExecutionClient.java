package com.sap.csc.poc.ems.service.brm.client;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.util.concurrent.SettableFuture;
import com.sap.csc.poc.ems.service.brm.client.template.ConcurrentRestTemplate;
import com.sap.csc.poc.ems.service.brm.config.property.BrmPropertyHolder.AuthenticationConfig.UriConfig.ExecutionUri;

@Component("brmExecutionClient")
public class BrmExecutionClient extends ConcurrentRestTemplate {

	@Autowired
	protected ExecutionUri executionUri;

	public String invoke(String ruleName, String executionBody) {
		// Url
		URI uri = UriComponentsBuilder.fromHttpUrl(executionUri.getRoot() + "rules/invoke")
				// Url Variables
				.queryParam("rule_name", ruleName)
				// Build Uri
				.build().toUri();

		// Request body
		RequestEntity<String> requestEntity = new RequestEntity<String>(executionBody, HttpMethod.POST, uri);

		// AsyncRestTemplate invoker
		final ListenableFuture<ResponseEntity<String>> future = getAsyncRestTemplate().exchange(
				// Http url
				uri,
				// Method
				HttpMethod.POST,
				// Request entity
				requestEntity,
				// Response
				String.class);

		// Set a value to return or set a failure Exception
		final SettableFuture<String> responseFuture = SettableFuture.create();

		// add callback listener
		future.addCallback((result) -> {
			// On success
			responseFuture.set(result.getBody());
		}, (ex) -> {
			// On failure
			responseFuture.setException(ex);
		});

		try {
			// Waits if necessary for at most the given time for the computation
			// to complete, and then retrieves its result, if available.
			return responseFuture.get(50, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}

	}

}
