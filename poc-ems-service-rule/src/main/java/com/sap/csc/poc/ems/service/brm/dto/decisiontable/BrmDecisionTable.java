package com.sap.csc.poc.ems.service.brm.dto.decisiontable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.poc.ems.service.brm.dto.metadata.BrmBody;
import com.sap.csc.poc.ems.service.brm.dto.metadata.BrmDecisionTableMetadata;

/**
 * @author Vincent Chen
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BrmDecisionTable implements BrmBody {

	private static final long serialVersionUID = 6223607696470828196L;

	@JsonProperty("content")
	private BrmDecisionTableContent content;

	@JsonProperty("meta_data")
	private BrmDecisionTableMetadata metadata;

	@JsonProperty("content")
	public BrmDecisionTableContent getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(BrmDecisionTableContent content) {
		this.content = content;
	}

	@JsonProperty("meta_data")
	public BrmDecisionTableMetadata getMetadata() {
		return metadata;
	}

	@JsonProperty("meta_data")
	public void setMetadata(BrmDecisionTableMetadata metadata) {
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
