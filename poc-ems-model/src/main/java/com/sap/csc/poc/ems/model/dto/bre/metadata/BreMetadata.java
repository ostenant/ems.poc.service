package com.sap.csc.poc.ems.model.dto.bre.metadata;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BreMetadata implements Serializable {

	private static final long serialVersionUID = -3832745683885192278L;

	@JsonProperty("package_id")
	private String packageId;

	@JsonProperty("object_name")
	private String objectName;

	@JsonProperty("object_suffix")
	private String objectSuffix;

	@JsonProperty("objectId")
	private String objectId;

	@JsonProperty("status")
	private String status;

	// TODO: Convert to Calendar and use custom converter
	@JsonProperty("last_modified")
	private String lastModified;

	// TODO: Convert to Calendar and use custom converter
	@JsonProperty("last_activated")
	private String lastActivated;

	@JsonProperty("package_id")
	public String getPackageId() {
		return packageId;
	}

	@JsonProperty("package_id")
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	@JsonProperty("object_name")
	public String getObjectName() {
		return objectName;
	}

	@JsonProperty("object_name")
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@JsonProperty("object_suffix")
	public String getObjectSuffix() {
		return objectSuffix;
	}

	@JsonProperty("object_suffix")
	public void setObjectSuffix(String objectSuffix) {
		this.objectSuffix = objectSuffix;
	}

	@JsonProperty("objectId")
	public String getObjectId() {
		return objectId;
	}

	@JsonProperty("objectId")
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("last_modified")
	public String getLastModified() {
		return lastModified;
	}

	@JsonProperty("last_modified")
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	@JsonProperty("last_activated")
	public String getLastActivated() {
		return lastActivated;
	}

	@JsonProperty("last_activated")
	public void setLastActivated(String lastActivated) {
		this.lastActivated = lastActivated;
	}

}
