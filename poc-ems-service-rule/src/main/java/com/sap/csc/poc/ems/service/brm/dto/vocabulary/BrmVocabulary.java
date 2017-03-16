package com.sap.csc.poc.ems.service.brm.dto.vocabulary;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.poc.ems.model.dto.bre.metadata.BreVocabularyMetadata;
import com.sap.csc.poc.ems.service.brm.dto.metadata.BrmBody;

/**
 * @author Vincent Chen
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BrmVocabulary implements BrmBody {

	private static final long serialVersionUID = 8093381356500409879L;

	@JsonProperty("meta_data")
	private BreVocabularyMetadata metadata;

	@JsonProperty("content")
	private BrmVocabularyContent content;

	@JsonProperty("meta_data")
	public BreVocabularyMetadata getMetadata() {
		return metadata;
	}

	@JsonProperty("meta_data")
	public void setMetadata(BreVocabularyMetadata metadata) {
		this.metadata = metadata;
	}

	@JsonProperty("content")
	public BrmVocabularyContent getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(BrmVocabularyContent content) {
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
