package com.sap.csc.poc.ems.model.dto.bre.decisiontable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.poc.ems.model.dto.bre.BreBody;
import com.sap.csc.poc.ems.model.dto.bre.metadata.BreMetadata;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BreDecisionTable implements BreBody {

	private static final long serialVersionUID = 6223607696470828196L;

	@JsonProperty("content")
	private BreDecisionTableContent content;

	@JsonProperty("meta_data")
	private BreMetadata metadata;

	@JsonProperty("content")
	public BreDecisionTableContent getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(BreDecisionTableContent content) {
		this.content = content;
	}

	@JsonProperty("meta_data")
	public BreMetadata getMetadata() {
		return metadata;
	}

	@JsonProperty("meta_data")
	public void setMetadata(BreMetadata metadata) {
		this.metadata = metadata;
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
