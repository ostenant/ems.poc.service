package com.sap.csc.poc.ems.service.brm.dto.decisiontable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BrmDecisionTableContent implements Serializable {

	private static final long serialVersionUID = 1848755725166889551L;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("status")
	private String status;

	@JsonProperty("ruleBody")
	private RuleBody ruleBody;

	@JsonProperty("vocabulary")
	private String vocabulary;

	@JsonProperty("output")
	private String output;

	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class RuleBody implements Serializable {

		@JsonIgnore
		private static final long serialVersionUID = 1990420749252348350L;

		@JsonProperty("type")
		private String type;

		@JsonProperty("hitPolicy")
		private String hitPolicy;

		@JsonProperty("content")
		private RuleBodyContent ruleBodyContent;

		@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		@JsonPropertyOrder({ "headers", "rows" })
		public static class RuleBodyContent implements Serializable {

			private static final long serialVersionUID = 7796654249136067388L;

			@JsonProperty("headers")
			private List<RuleBodyHeader> ruleBodyHeaders = new ArrayList<RuleBodyHeader>();

			@JsonProperty("rows")
			private List<RuleBodyRow> ruleBodyRows = new ArrayList<RuleBodyRow>();

			@JsonProperty("headers")
			public List<RuleBodyHeader> getRuleBodyHeaders() {
				return ruleBodyHeaders;
			}

			@JsonProperty("headers")
			public void setRuleBodyHeaders(List<RuleBodyHeader> ruleBodyHeaders) {
				this.ruleBodyHeaders = ruleBodyHeaders;
			}

			@JsonProperty("rows")
			public List<RuleBodyRow> getRuleBodyRows() {
				return ruleBodyRows;
			}

			@JsonProperty("rows")
			public void setRuleBodyRows(List<RuleBodyRow> ruleBodyRows) {
				this.ruleBodyRows = ruleBodyRows;
			}

			@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class RuleBodyHeader implements Serializable {

				private static final long serialVersionUID = 1842837602916199723L;

				@JsonProperty("colID")
				private String colID;

				@JsonProperty("type")
				protected String type;

				@JsonProperty("name")
				private String name;

				@JsonProperty("expression")
				private String expression;

				@JsonProperty("alias")
				private String alias;

				@JsonProperty("width")
				protected Integer width;

				@JsonProperty("colID")
				public String getColID() {
					return colID;
				}

				@JsonProperty("colID")
				public void setColID(String colID) {
					this.colID = colID;
				}

				@JsonProperty("type")
				public String getType() {
					return type;
				}

				@JsonProperty("type")
				public void setType(String type) {
					this.type = type;
				}

				@JsonProperty("name")
				public String getName() {
					return name;
				}

				@JsonProperty("name")
				public void setName(String name) {
					this.name = name;
				}

				@JsonProperty("expression")
				public String getExpression() {
					return expression;
				}

				@JsonProperty("expression")
				public void setExpression(String expression) {
					this.expression = expression;
				}

				@JsonProperty("alias")
				public String getAlias() {
					return alias;
				}

				@JsonProperty("alias")
				public void setAlias(String alias) {
					this.alias = alias;
				}

				@JsonProperty("width")
				public Integer getWidth() {
					return width;
				}

				@JsonProperty("width")
				public void setWidth(Integer width) {
					this.width = width;
				}

			}

			@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
			public static class RuleBodyRow implements Serializable {

				private static final long serialVersionUID = -2631393611579842687L;

				@JsonProperty("rowID")
				private String rowID;

				@JsonProperty("row")
				private List<RowItem> rowItems = new ArrayList<RowItem>();

				@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class RowItem implements Serializable {

					@JsonIgnore
					private static final long serialVersionUID = 4993500805881676807L;

					@JsonProperty("colID")
					private String colID;

					@JsonProperty("content")
					private String content;

					@JsonProperty("span")
					private String span;

					@JsonProperty("colID")
					public String getColID() {
						return colID;
					}

					@JsonProperty("colID")
					public void setColID(String colID) {
						this.colID = colID;
					}

					@JsonProperty("content")
					public String getContent() {
						return content;
					}

					@JsonProperty("content")
					public void setContent(String content) {
						this.content = content;
					}

					@JsonProperty("span")
					public String getSpan() {
						return span;
					}

					@JsonProperty("span")
					public void setSpan(String span) {
						this.span = span;
					}

				}

				@JsonProperty("rowID")
				public String getRowID() {
					return rowID;
				}

				@JsonProperty("rowID")
				public void setRowID(String rowID) {
					this.rowID = rowID;
				}

				@JsonProperty("row")
				public List<RowItem> getRowItems() {
					return rowItems;
				}

				@JsonProperty("row")
				public void setRowItems(List<RowItem> rowItems) {
					this.rowItems = rowItems;
				}

			}

		}

		@JsonProperty("type")
		public String getType() {
			return type;
		}

		@JsonProperty("type")
		public void setType(String type) {
			this.type = type;
		}

		@JsonProperty("hitPolicy")
		public String getHitPolicy() {
			return hitPolicy;
		}

		@JsonProperty("hitPolicy")
		public void setHitPolicy(String hitPolicy) {
			this.hitPolicy = hitPolicy;
		}

		@JsonProperty("content")
		public RuleBodyContent getRuleBodyContent() {
			return ruleBodyContent;
		}

		@JsonProperty("content")
		public void setRuleBodyContent(RuleBodyContent ruleBodyContent) {
			this.ruleBodyContent = ruleBodyContent;
		}

	}

}
