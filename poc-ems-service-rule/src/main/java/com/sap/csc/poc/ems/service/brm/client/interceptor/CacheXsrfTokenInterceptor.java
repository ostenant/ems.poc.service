package com.sap.csc.poc.ems.service.brm.client.interceptor;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.AsyncClientHttpRequestExecution;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.sap.csc.poc.ems.service.brm.client.template.DefaultRestTemplate;
import com.sap.csc.poc.ems.service.brm.config.property.BrmPropertyHolder;
import com.sap.csc.poc.ems.service.brm.config.property.BrmPropertyHolder.AuthenticationConfig.UriConfig;
import com.sap.csc.poc.ems.service.brm.config.property.BrmPropertyHolder.AuthenticationConfig.UriConfig.RepositoryUri;

@Component
public class CacheXsrfTokenInterceptor implements AsyncClientHttpRequestInterceptor, ClientHttpRequestInterceptor {

	private final ConcurrentHashMap<String, Pair<String, Calendar>> xsrfTokenMap = new ConcurrentHashMap<String, Pair<String, Calendar>>(
			2);

	@Autowired
	@Qualifier("defaultRestTemplate")
	protected DefaultRestTemplate defaultRestTemplate;

	@Autowired
	protected BrmPropertyHolder brmPropertyHolder;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		addCsrfHeaders(request);
		// waits until have a result, then returns the result
		return execution.execute(request, body);
	}

	@Override
	public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request, byte[] body,
			AsyncClientHttpRequestExecution execution) throws IOException {
		addCsrfHeaders(request);
		// returns a Future immediately which can be processed after handled
		return execution.executeAsync(request, body);
	}

	private void addCsrfHeaders(HttpRequest request) {
		String url = request.getURI().toString().trim();
		UriConfig uri = brmPropertyHolder.getUri();
		if (request.getMethod() == HttpMethod.GET && StringUtils.equalsIgnoreCase(url, uri.getRepository().getXsrf())
				|| StringUtils.equalsIgnoreCase(url, uri.getExecution().getXsrf())) {
			request.getHeaders().add("x-csrf-token", "Fetch");
			addBasicAuthHeader(request);
		} else if (url.startsWith(uri.getRepository().getRoot())) {
			request.getHeaders().add("x-csrf-token", this.getXsrfToken(uri.getRepository().getRoot()));
			addBasicAuthHeader(request);
		} else if (url.startsWith(uri.getExecution().getRoot())) {
			request.getHeaders().add("x-csrf-token", this.getXsrfToken(uri.getExecution().getRoot()));
			addBasicAuthHeader(request);
		}
	}

	private void addBasicAuthHeader(HttpRequest request) {
		String authorizationHeader = request.getHeaders().getFirst("Authorization");
		if (StringUtils.isBlank(authorizationHeader)) {
			request.getHeaders().add("Authorization", "Basic " + brmPropertyHolder.getAuthentication().getBasicToken());
		}
	}

	@Cacheable()
	private String getXsrfToken(String path) {
		if (xsrfTokenMap.containsKey(path)) {
			Pair<String, Calendar> candidate = xsrfTokenMap.get(path);
			if (DateUtils.truncatedCompareTo(candidate.getSecond().getTime(), getExpirationTime(),
					Calendar.SECOND) > 0) {
				return candidate.getFirst();
			} else {
				xsrfTokenMap.remove(path);
			}
		}

		RepositoryUri repository = brmPropertyHolder.getUri().getRepository();
		if (StringUtils.equalsIgnoreCase(repository.getRoot(), path)) {
			String designTimeToken = getDesignTimeXsrfToken();
			xsrfTokenMap.put(path, Pair.of(designTimeToken, Calendar.getInstance()));
			return designTimeToken;
		} else {
			String runTimeToken = getRunTimeXsrfToken();
			xsrfTokenMap.put(path, Pair.of(runTimeToken, Calendar.getInstance()));
			return runTimeToken;
		}

	}

	private String getDesignTimeXsrfToken() {

		final String designTimeXsrfToken = defaultRestTemplate.getRestTemplate()
				.exchange(
						new RequestEntity<Object>(
								// Method
								HttpMethod.GET,
								// URI
								URI.create(brmPropertyHolder.getUri().getRepository().getXsrf())),
						// Response
						String.class)
				.getHeaders().getFirst("X-Csrf-Token");

		return designTimeXsrfToken;
	}

	private String getRunTimeXsrfToken() {
		final String runtimeXsrfToken = defaultRestTemplate.getRestTemplate()
				.exchange(
						new RequestEntity<Object>(
								// Method
								HttpMethod.GET,
								// URI
								URI.create(brmPropertyHolder.getUri().getExecution().getXsrf())),
						// Response
						String.class)
				.getHeaders().getFirst("x-csrf-token");

		return runtimeXsrfToken;
	}

	private Date getExpirationTime() {
		return DateUtils.addSeconds(Calendar.getInstance().getTime(), -20 * 60);
	}

}
