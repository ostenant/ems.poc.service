package com.sap.csc.poc.ems.model.dto.bre.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreVocabularyMetadata extends BreMetadata {

	private static final long serialVersionUID = -5799421263700690300L;

	@JsonProperty("object_suffix")
	private final String objectSuffix = "vocabulary";

	@JsonProperty("object_suffix")
	public String getObjectSuffix() {
		return objectSuffix;
	}

}
