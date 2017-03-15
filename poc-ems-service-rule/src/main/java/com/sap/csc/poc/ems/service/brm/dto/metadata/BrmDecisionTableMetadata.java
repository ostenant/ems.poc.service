package com.sap.csc.poc.ems.service.brm.dto.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrmDecisionTableMetadata extends BrmMetadata {

	private static final long serialVersionUID = -5799421263700690300L;

	@JsonProperty("object_suffix")
	private final String objectSuffix = "decisiontable";

	@JsonProperty("object_suffix")
	public String getObjectSuffix() {
		return objectSuffix;
	}

}
