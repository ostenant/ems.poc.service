package com.sap.csc.poc.ems.model.dto.bre.vacabulary;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.poc.ems.model.dto.bre.BreBody;
import com.sap.csc.poc.ems.model.dto.bre.metadata.BreVocabularyMetadata;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BreVocabulary implements BreBody {

	private static final long serialVersionUID = 8093381356500409879L;

	@JsonProperty("meta_data")
	private BreVocabularyMetadata metadata;

	@JsonProperty("content")
	private BreVocabularyContent content;

	@JsonProperty("meta_data")
	public BreVocabularyMetadata getMetadata() {
		return metadata;
	}

	@JsonProperty("meta_data")
	public void setMetadata(BreVocabularyMetadata metadata) {
		this.metadata = metadata;
	}

	@JsonProperty("content")
	public BreVocabularyContent getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(BreVocabularyContent content) {
		this.content = content;
	}

	@JsonIgnore
	public String getEntireName() {
		StringBuilder builder = new StringBuilder();
		builder.append(metadata.getPackageId())//
				.append("::").append(metadata.getObjectName())//
				.append(".").append(metadata.getObjectSuffix());
		return builder.toString();
	}

}
