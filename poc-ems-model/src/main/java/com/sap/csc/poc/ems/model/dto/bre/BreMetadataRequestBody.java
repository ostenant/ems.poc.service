package com.sap.csc.poc.ems.model.dto.bre;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreMetadataRequestBody implements Serializable {

	private static final long serialVersionUID = -7023037165551785800L;

	@JsonProperty("meta_data")
	private Metadata metadata;

	@JsonProperty("content")
	private Content content;

	public static class Metadata {

		@JsonProperty("package_id")
		private String packageId;

		@JsonProperty("object_name")
		private String objectName;

		@JsonProperty("object_suffix")
		private String objectSuffix;

		// TODO: Convert to Calendar and use custom converter
		@JsonProperty("last_modified")
		private String lastModified;

		public String getPackageId() {
			return packageId;
		}

		public void setPackageId(String packageId) {
			this.packageId = packageId;
		}

		public String getObjectName() {
			return objectName;
		}

		public void setObjectName(String objectName) {
			this.objectName = objectName;
		}

		public String getObjectSuffix() {
			return objectSuffix;
		}

		public void setObjectSuffix(String objectSuffix) {
			this.objectSuffix = objectSuffix;
		}

		public String getLastModified() {
			return lastModified;
		}

		public void setLastModified(String lastModified) {
			this.lastModified = lastModified;
		}
	}

	public static class Content {

		@JsonProperty("dataObjects")
		private List<Map<String, Object>> dataObjects;

		@JsonProperty("ruleBody")
		private Map<String, Object> ruleBody;

		public List<Map<String, Object>> getDataObjects() {
			return dataObjects;
		}

		public void setDataObjects(List<Map<String, Object>> dataObjects) {
			this.dataObjects = dataObjects;
		}

		public Map<String, Object> getRuleBody() {
			return ruleBody;
		}

		public void setRuleBody(Map<String, Object> ruleBody) {
			this.ruleBody = ruleBody;
		}
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
}
