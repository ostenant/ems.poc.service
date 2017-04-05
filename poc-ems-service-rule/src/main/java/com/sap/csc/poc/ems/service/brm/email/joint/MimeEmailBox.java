package com.sap.csc.poc.ems.service.brm.email.joint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MimeEmailBox extends PlainTextEmailBox {

	private static final long serialVersionUID = -7876719465987547204L;

	public static final String DEFAULT_ENCCODING = "UTF-8";

	/**
	 * Static template file name
	 */
	protected String templateName;

	/**
	 * Template model map
	 */
	protected Map<String, Object> modelObject;

	/**
	 * Schedule time
	 */
	protected Long when;

	/**
	 * Schedule properties
	 */
	protected Integer priority;

	/**
	 * Email attachments
	 */
	protected List<MimeEmailAttachment> mimeEmailAttachments = new ArrayList<MimeEmailAttachment>();

	/**
	 * Email pictures
	 */
	private List<MimeEmailInlinePicture> mimeEmailPictures = new ArrayList<MimeEmailInlinePicture>();

	public Long getWhen() {
		return when;
	}

	public void setWhen(Long when) {
		this.when = when;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Map<String, Object> getModelObject() {
		return modelObject;
	}

	public void setModelObject(Map<String, Object> modelObject) {
		this.modelObject = modelObject;
	}

	public List<MimeEmailAttachment> getMimeEmailAttachments() {
		return mimeEmailAttachments;
	}

	public void setMimeEmailAttachments(List<MimeEmailAttachment> mimeEmailAttachments) {
		this.mimeEmailAttachments = mimeEmailAttachments;
	}

	public List<MimeEmailInlinePicture> getMimeEmailPictures() {
		return mimeEmailPictures;
	}

	public void setMimeEmailPictures(List<MimeEmailInlinePicture> mimeEmailPictures) {
		this.mimeEmailPictures = mimeEmailPictures;
	}

	@Override
	public String toString() {
		return "MimeEmailBox [mimeEmailAttachments=" + mimeEmailAttachments + ", mimeEmailPictures=" + mimeEmailPictures
				+ "]";
	}

}
