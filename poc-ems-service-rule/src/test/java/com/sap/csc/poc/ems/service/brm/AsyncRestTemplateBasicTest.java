package com.sap.csc.poc.ems.service.brm;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRequestCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResponseExtractor;

public class AsyncRestTemplateBasicTest {

	@Test
	public void test() {

		AsyncRestTemplate asycTemp = new AsyncRestTemplate();

		String url = "https://ems-poc.cfapps.sap.hana.ondemand.com/api/v1/entitlements";

		HttpMethod method = HttpMethod.GET;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		AsyncRequestCallback requestCallback = new AsyncRequestCallback() {
			@Override
			public void doWithRequest(AsyncClientHttpRequest request) throws IOException {
				System.out.println(request.getURI());
			}
		};

		ResponseExtractor<String> responseExtractor = new ResponseExtractor<String>() {
			@Override
			public String extractData(ClientHttpResponse response) throws IOException {
				StringWriter writer = new StringWriter(2048);
				IOUtils.copy(response.getBody(), writer, Charset.forName("utf-8"));
				return new String(writer.getBuffer());
			}
		};

		// Map<String, String> urlVariable = new HashMap<String, String>();
		// urlVariable.put("q", "p");

		ListenableFuture<String> future = asycTemp.execute(url, method, requestCallback, responseExtractor);
		try {
			// waits for the result
			String result = future.get();
			System.out.println(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}