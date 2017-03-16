package com.sap.csc.poc.ems.service.brm.dto.vocabulary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vincent Chen
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BrmVocabularyContent implements Serializable {

	private static final long serialVersionUID = 8460969283691153750L;

	@JsonProperty("dataObjects")
	private List<DataObject> dataObjects = new ArrayList<DataObject>();

	@JsonProperty("dataObjects")
	public List<DataObject> getDataObjects() {
		return dataObjects;
	}

	@JsonProperty("dataObjects")
	public void setDataObjects(List<DataObject> dataObjects) {
		this.dataObjects = dataObjects;
	}

	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class DataObject implements Serializable {

		private static final long serialVersionUID = 5778214650117700624L;

		@JsonProperty("name")
		private String name;

		@JsonProperty("description")
		private String description;

		@JsonProperty("usage")
		private String usage;

		@JsonProperty("attributes")
		private List<Attribute> attributes = new ArrayList<Attribute>();

		@JsonProperty("associations")
		private List<Association> associations = new ArrayList<Association>();

		@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
		public static class Attribute implements Serializable {

			private static final long serialVersionUID = -9141255468152876232L;

			@JsonProperty("name")
			private String name;

			@JsonProperty("description")
			private String description;

			@JsonProperty("dataType")
			private String dataType;

			@JsonProperty("sourceType")
			private String sourceType;

			@JsonProperty("name")
			public String getName() {
				return name;
			}

			@JsonProperty("name")
			public void setName(String name) {
				this.name = name;
			}

			@JsonProperty("description")
			public String getDescription() {
				return description;
			}

			@JsonProperty("description")
			public void setDescription(String description) {
				this.description = description;
			}

			@JsonProperty("dataType")
			public String getDataType() {
				return dataType;
			}

			@JsonProperty("dataType")
			public void setDataType(String dataType) {
				this.dataType = dataType;
			}

			@JsonProperty("sourceType")
			public String getSourceType() {
				return sourceType;
			}

			@JsonProperty("sourceType")
			public void setSourceType(String sourceType) {
				this.sourceType = sourceType;
			}

		}

		@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
		public static class Association implements Serializable {

			private static final long serialVersionUID = 6691833797175282316L;

			@JsonProperty("name")
			private String name;

			@JsonProperty("target")
			private String target;

			@JsonProperty("cardinality")
			private String cardinality;

			@JsonProperty("attributeMappings")
			private List<AttributeMapping> attributeMappings = new ArrayList<AttributeMapping>();

			@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class AttributeMapping implements Serializable {

				private static final long serialVersionUID = -307366086665832290L;

				@JsonProperty("source")
				private String source;

				@JsonProperty("target")
				private String target;

				@JsonProperty("source")
				public String getSource() {
					return source;
				}

				@JsonProperty("source")
				public void setSource(String source) {
					this.source = source;
				}

				@JsonProperty("target")
				public String getTarget() {
					return target;
				}

				@JsonProperty("target")
				public void setTarget(String target) {
					this.target = target;
				}

			}

			@JsonProperty("name")
			public String getName() {
				return name;
			}

			@JsonProperty("name")
			public void setName(String name) {
				this.name = name;
			}

			@JsonProperty("target")
			public String getTarget() {
				return target;
			}

			@JsonProperty("target")
			public void setTarget(String target) {
				this.target = target;
			}

			@JsonProperty("cardinality")
			public String getCardinality() {
				return cardinality;
			}

			@JsonProperty("cardinality")
			public void setCardinality(String cardinality) {
				this.cardinality = cardinality;
			}

			@JsonProperty("attributeMappings")
			public List<AttributeMapping> getAttributeMappings() {
				return attributeMappings;
			}

			@JsonProperty("attributeMappings")
			public void setAttributeMappings(List<AttributeMapping> attributeMappings) {
				this.attributeMappings = attributeMappings;
			}

		}

		@JsonProperty("name")
		public String getName() {
			return name;
		}

		@JsonProperty("name")
		public void setName(String name) {
			this.name = name;
		}

		@JsonProperty("description")
		public String getDescription() {
			return description;
		}

		@JsonProperty("description")
		public void setDescription(String description) {
			this.description = description;
		}

		@JsonProperty("usage")
		public String getUsage() {
			return usage;
		}

		@JsonProperty("usage")
		public void setUsage(String usage) {
			this.usage = usage;
		}

		@JsonProperty("attributes")
		public List<Attribute> getAttributes() {
			return attributes;
		}

		@JsonProperty("attributes")
		public void setAttributes(List<Attribute> attributes) {
			this.attributes = attributes;
		}

		@JsonProperty("associations")
		public List<Association> getAssociations() {
			return associations;
		}

		@JsonProperty("associations")
		public void setAssociations(List<Association> associations) {
			this.associations = associations;
		}

	}

}
