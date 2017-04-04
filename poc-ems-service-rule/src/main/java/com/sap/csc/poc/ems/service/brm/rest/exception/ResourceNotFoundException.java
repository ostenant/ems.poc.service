package com.sap.csc.poc.ems.service.brm.rest.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Xiaoyue Xiao
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2565431806475335331L;

	private String resourceName;
	private Long id;

	public String getResourceName() {
		return resourceName;
	}

	public ResourceNotFoundException setResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}

	public Long getId() {
		return id;
	}

	public ResourceNotFoundException setId(Long id) {
		this.id = id;
		return this;
	}

	@Override
	public String getMessage() {
		return StringUtils.capitalize(resourceName) + " with id " + id + " is not found.";
	}

}
