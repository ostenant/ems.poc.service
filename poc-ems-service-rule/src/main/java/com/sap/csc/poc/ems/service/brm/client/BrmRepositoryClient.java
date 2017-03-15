package com.sap.csc.poc.ems.service.brm.client;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.csc.poc.ems.model.dto.bre.BreBody;
import com.sap.csc.poc.ems.service.brm.client.template.ConcurrentRestTemplate;
import com.sap.csc.poc.ems.service.brm.config.property.BrmPropertyHolder.AuthenticationConfig.UriConfig.RepositoryUri;

@Component("brmRepositoryClient")
public class BrmRepositoryClient extends ConcurrentRestTemplate {

	private static final String OBJECT_NAME_TEMPLATE = "%s.%s";

	@Autowired
	protected RepositoryUri repositoryUri;

	@Autowired
	protected ObjectMapper objectMapper;

	protected String DESIGN_TIME_REPO;

	protected String DESIGN_TIME_REPO_ACTIVATE;

	@PostConstruct
	public void init() {
		DESIGN_TIME_REPO = repositoryUri.getRoot() + "design-time/repo";
		DESIGN_TIME_REPO_ACTIVATE = repositoryUri.getRoot() + "design-time/repo/activate";
	}

	public String retrieve(String objectName, String objectSuffix) {
		String body = getRestTemplate().exchange(
				new RequestEntity<Object>(
						// Method
						HttpMethod.GET,
						// Uri
						UriComponentsBuilder.fromHttpUrl(DESIGN_TIME_REPO)
								// Url Variables
								.queryParam("object_name",
										String.format(OBJECT_NAME_TEMPLATE, objectName, objectSuffix))
								// Build
								.build().toUri()),
				// Response
				String.class).getBody();
		return body;
	}

	public <T extends BreBody> Pair<HttpStatus, String> create(T breBody) {
		final ResponseEntity<String> responseEntity = getRestTemplate().exchange(
				new RequestEntity<T>(
						// Body
						breBody,
						// Method
						HttpMethod.POST,
						// Uri
						UriComponentsBuilder.fromHttpUrl(DESIGN_TIME_REPO)
								// Build
								.build().toUri()),
				// Response
				String.class);

		// 204 NO_CREATE
		if (HttpStatus.CREATED == responseEntity.getStatusCode()) {
			Pair<HttpStatus, String> responsePair = Pair.of(responseEntity.getStatusCode(), breBody.getEntireName());
			return responsePair;
		} else {
			Pair<HttpStatus, String> responsePair = Pair.of(responseEntity.getStatusCode(), responseEntity.getBody());
			return responsePair;
		}

	}

	public <T extends BreBody> Pair<HttpStatus, String> update(T breBody) {

		final ResponseEntity<String> responseEntity = getRestTemplate().exchange(
				new RequestEntity<T>(
						// Body
						breBody,
						// Method
						HttpMethod.PATCH,
						// Uri
						UriComponentsBuilder.fromHttpUrl(DESIGN_TIME_REPO)
								// Build
								.build().toUri()),
				// Response
				String.class);

		// 204 NO_CONTENT
		if (HttpStatus.NO_CONTENT == responseEntity.getStatusCode()) {
			Pair<HttpStatus, String> responsePair = Pair.of(responseEntity.getStatusCode(), breBody.getEntireName());
			return responsePair;
		} else {
			Pair<HttpStatus, String> responsePair = Pair.of(responseEntity.getStatusCode(), responseEntity.getBody());
			return responsePair;
		}
	}

	public Pair<HttpStatus, String> delete(String objectName, String objectSuffix) {
		final ResponseEntity<String> responseEntity = getRestTemplate().exchange(
				new RequestEntity<Object>(
						// Method
						HttpMethod.DELETE,
						// Uri
						UriComponentsBuilder.fromHttpUrl(DESIGN_TIME_REPO)
								// Url Variables
								.queryParam("object_name",
										String.format(OBJECT_NAME_TEMPLATE, objectName, objectSuffix))
								// Build
								.build().toUri()),
				// Response
				String.class);

		Pair<HttpStatus, String> responsePair = Pair.of(responseEntity.getStatusCode(), responseEntity.getBody());

		return responsePair;
	}

	public Pair<HttpStatus, String> activate(String objectName, String objectSuffix) {
		final ResponseEntity<String> responseEntity = getRestTemplate().exchange(
				new RequestEntity<Object>(
						// Method
						HttpMethod.POST,
						// Uri
						UriComponentsBuilder.fromHttpUrl(DESIGN_TIME_REPO_ACTIVATE)
								// Url Variables
								.queryParam("object_name",
										String.format(OBJECT_NAME_TEMPLATE, objectName, objectSuffix))
								// Build
								.build().toUri()),
				// Response
				String.class);

		Pair<HttpStatus, String> responsePair = Pair.of(responseEntity.getStatusCode(), responseEntity.getBody());

		return responsePair;
	}

}
