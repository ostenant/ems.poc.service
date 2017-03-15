package com.sap.csc.poc.ems.service.bre.rest.inteceptor;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.service.bre.BreConfig;
import com.sap.csc.poc.ems.service.bre.BreConfig.AuthenticationConfig.UriConfig;
import com.sap.csc.poc.ems.service.bre.BreConfig.AuthenticationConfig.UriConfig.RepositoryUri;
import com.sap.csc.poc.ems.service.bre.rest.template.BreRepoRestTemplate;

@Component
public class CacheXsrfTokenInterceptor implements ClientHttpRequestInterceptor {

	// Replace by expiration-enabled collection
	private final ConcurrentHashMap<String, Pair<String, Calendar>> xsrfTokenMap = new ConcurrentHashMap<String, Pair<String, Calendar>>(
			2);

	@Autowired
	protected BreConfig breConfig;

	@Autowired
	protected BreRepoRestTemplate breRepoRestTemplate;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String url = request.getURI().toString().trim();
		UriConfig uri = breConfig.getUri();
		if (request.getMethod() == HttpMethod.GET && StringUtils.equalsIgnoreCase(url, uri.getRepository().getXsrf())
				|| StringUtils.equalsIgnoreCase(url, uri.getInvoker().getXsrf())) {
			request.getHeaders().add("x-csrf-token", "fetch");
			request.getHeaders().add("Authorization", "Basic " + breConfig.getAuthentication().getBasicToken());
		} else if (url.startsWith(uri.getRepository().getRoot())) {
			request.getHeaders().add("x-csrf-token", this.getXsrfToken(uri.getRepository().getRoot()));
		} else if (url.startsWith(uri.getInvoker().getRoot())) {
			request.getHeaders().add("x-csrf-token", this.getXsrfToken(uri.getInvoker().getRoot()));
		}
		return execution.execute(request, body);
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

		RepositoryUri repository = breConfig.getUri().getRepository();
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
		return breRepoRestTemplate.exchange(
				new RequestEntity<Object>(
						// Method
						HttpMethod.GET,
						// URI
						URI.create(breConfig.getUri().getRepository().getXsrf())),
				// Response
				String.class).getHeaders().getFirst("x-csrf-token");
	}

	private String getRunTimeXsrfToken() {
		return breRepoRestTemplate.exchange(
				new RequestEntity<Object>(
						// Method
						HttpMethod.GET,
						// URI
						URI.create(breConfig.getUri().getInvoker().getXsrf())),
				// Response
				String.class).getHeaders().getFirst("x-csrf-token");
	}

	private Date getExpirationTime() {
		return DateUtils.addSeconds(Calendar.getInstance().getTime(), -20 * 60);
	}
}
