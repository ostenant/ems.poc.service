package com.sap.csc.poc.ems.service.brm.client.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.AsyncClientHttpRequestExecution;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author Vincent Chen
 *
 */
@Component
public class EvictClutterHeadersInterceptor implements AsyncClientHttpRequestInterceptor, ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		evictClutterHeaders(request);
		return execution.execute(request, body);
	}

	@Override
	public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request, byte[] body,
			AsyncClientHttpRequestExecution execution) throws IOException {
		evictClutterHeaders(request);
		return execution.executeAsync(request, body);
	}

	private void evictClutterHeaders(HttpRequest request) {
		request.getHeaders().clear();
	}

}
