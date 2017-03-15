package com.sap.csc.poc.ems.service.brm.client.template;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.sap.csc.poc.ems.service.brm.client.handler.IgnoreResponseErrorHandler;
import com.sap.csc.poc.ems.service.brm.client.interceptor.CacheXsrfTokenInterceptor;
import com.sap.csc.poc.ems.service.brm.client.interceptor.JsonContentTypeInterceptor;

public class ConcurrentRestTemplate {

	protected HttpComponentsClientHttpRequestFactory clientHttpRequestFactory;

	protected static volatile ThreadLocal<RestTemplate> restTemplateLocal = new ThreadLocal<RestTemplate>();

	protected static volatile ThreadLocal<AsyncRestTemplate> asyncRestTemplateLocal = new ThreadLocal<AsyncRestTemplate>();

	private RestTemplate restTemplate = new RestTemplate();

	private AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

	@Autowired
	protected CacheXsrfTokenInterceptor cacheXsrfTokenInterceptor;

	@Autowired
	protected JsonContentTypeInterceptor jsonContentTypeInterceptor;

	@Autowired
	protected IgnoreResponseErrorHandler errorHandler;

	@Autowired
	public void setClientHttpRequestFactory(HttpComponentsClientHttpRequestFactory clientHttpRequestFactory) {
		this.clientHttpRequestFactory = clientHttpRequestFactory;
	}

	@PostConstruct
	public void initial() {
		initialRestTemplate();
		initialAsyncRestTemplate();
	}

	private void initialRestTemplate() {
		restTemplate.setRequestFactory(clientHttpRequestFactory);
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (interceptors.size() > 0) {
			for (ClientHttpRequestInterceptor interceptor : interceptors) {
				if (interceptor instanceof CacheXsrfTokenInterceptor) {
					restTemplateLocal.set(restTemplate);
					return;
				}
			}

		} else {
			restTemplate.getInterceptors().add(cacheXsrfTokenInterceptor);
			restTemplate.getInterceptors().add(jsonContentTypeInterceptor);
			restTemplate.setErrorHandler(errorHandler);
		}

		restTemplateLocal.set(restTemplate);
	}

	private void initialAsyncRestTemplate() {
		// AsyncClientHttpRequestFactory asyncRequestFactory = new
		// HttpComponentsAsyncClientHttpRequestFactory();
		// asyncRestTemplate.setAsyncRequestFactory(asyncRequestFactory);
		List<AsyncClientHttpRequestInterceptor> interceptors = asyncRestTemplate.getInterceptors();
		if (interceptors.size() > 0) {
			for (AsyncClientHttpRequestInterceptor interceptor : interceptors) {
				if (interceptor instanceof CacheXsrfTokenInterceptor) {
					asyncRestTemplateLocal.set(asyncRestTemplate);
					return;
				}
			}

		} else {
			asyncRestTemplate.getInterceptors().add(cacheXsrfTokenInterceptor);
			asyncRestTemplate.getInterceptors().add(jsonContentTypeInterceptor);
			asyncRestTemplate.setErrorHandler(errorHandler);
		}

		asyncRestTemplateLocal.set(asyncRestTemplate);
	}

	public RestTemplate getRestTemplate() {
		if (restTemplateLocal.get() == null) {
			initialRestTemplate();
		}
		return restTemplateLocal.get();
	}

	public AsyncRestTemplate getAsyncRestTemplate() {
		if (asyncRestTemplateLocal.get() == null) {
			initialAsyncRestTemplate();
		}
		return asyncRestTemplateLocal.get();
	}

}
