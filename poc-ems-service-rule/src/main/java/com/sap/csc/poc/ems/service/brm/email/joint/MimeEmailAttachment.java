package com.sap.csc.poc.ems.service.brm.email.joint;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import com.google.common.io.Files;

public class MimeEmailAttachment implements Serializable {

	private static final long serialVersionUID = 6642164686028379895L;

	private static final String CSV = "csv";
	private static final String DOCX = "docx";
	private static final String DOC = "doc";
	private static final String PDF = "pdf";
	private static final String EXCEL = "xls";
	private static final String TEXT = "txt";
	private static final String ZIP = "zip";
	private static final String AVI = "avi";
	private static final String JSON = "json";
	private static final String XML = "xml";

	private static Collection<String> ACCEPTED_EXTENSION_COLLECTION = Arrays.asList(CSV, DOCX, DOC, PDF, EXCEL, TEXT,
			ZIP, AVI, JSON, XML);

	protected static final String[] ACCEPTED_EXTENSION = ACCEPTED_EXTENSION_COLLECTION
			.toArray(new String[ACCEPTED_EXTENSION_COLLECTION.size()]);

	private static final String EMPTY_EXCEPTION = "File doesn't exist!";

	/**
	 * Name
	 */
	private String attachmentName;

	/**
	 * Base Name
	 */
	private String attachmentBaseName;

	/**
	 * Name suffix
	 */
	private String attachmentExtension;

	/**
	 * Type
	 */
	private MediaType attachmentMediaType;

	/**
	 * Attachment
	 */
	private File attachmentFile;

	public MimeEmailAttachment() {
	}

	public MimeEmailAttachment(File attachmentFile) {
		this(null, attachmentFile);
	}

	public MimeEmailAttachment(String attachmentBaseName, File attachmentFile) {
		if (Files.isFile().apply(attachmentFile)
				&& StringUtils.lastIndexOf(attachmentFile.getName(), FilenameUtils.EXTENSION_SEPARATOR_STR) > -1) {
			this.attachmentFile = attachmentFile;
		} else {
			throw new RuntimeException(EMPTY_EXCEPTION);
		}

		this.attachmentBaseName = StringUtils.isNotBlank(attachmentBaseName) ? attachmentBaseName
				: FilenameUtils.getBaseName(attachmentFile.getName());
		this.attachmentExtension = FilenameUtils.getExtension(attachmentFile.getName());
		this.attachmentName = StringUtils.join(attachmentBaseName, FilenameUtils.EXTENSION_SEPARATOR_STR,
				attachmentExtension);

		if (ArrayUtils.contains(ACCEPTED_EXTENSION, attachmentExtension)) {
			switch (attachmentExtension) {
			case CSV:
				setAttachmentMediaType(MediaType.TEXT_HTML);
				break;
			case DOCX:
				setAttachmentMediaType(MediaType.TEXT_HTML);
				break;
			case DOC:
				setAttachmentMediaType(MediaType.TEXT_HTML);
				break;
			case PDF:
				setAttachmentMediaType(MediaType.APPLICATION_PDF);
				break;
			case EXCEL:
				setAttachmentMediaType(MediaType.TEXT_HTML);
				break;
			case TEXT:
				setAttachmentMediaType(MediaType.TEXT_PLAIN);
				break;
			case ZIP:
				setAttachmentMediaType(MediaType.TEXT_HTML);
				break;
			case AVI:
				setAttachmentMediaType(MediaType.APPLICATION_OCTET_STREAM);
				break;
			case JSON:
				setAttachmentMediaType(MediaType.APPLICATION_JSON_UTF8);
				break;
			case XML:
				setAttachmentMediaType(MediaType.APPLICATION_XML);
				break;

			}
		} else {
			setAttachmentMediaType(MediaType.TEXT_PLAIN);
		}
	}

	public String getAttachmentBaseName() {
		return attachmentBaseName;
	}

	public void setAttachmentBaseName(String attachmentBaseName) {
		this.attachmentBaseName = attachmentBaseName;
	}

	public String getAttachmentExtension() {
		return attachmentExtension;
	}

	public void setAttachmentExtension(String attachmentExtension) {
		this.attachmentExtension = attachmentExtension;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public MediaType getAttachmentMediaType() {
		return attachmentMediaType;
	}

	public void setAttachmentMediaType(MediaType attachmentMediaType) {
		this.attachmentMediaType = attachmentMediaType;
	}

	public File getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(File attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	@Override
	public String toString() {
		return "MimeEmailAttachment [attachmentName=" + attachmentName + ", attachmentBaseName=" + attachmentBaseName
				+ ", attachmentExtension=" + attachmentExtension + ", attachmentMediaType=" + attachmentMediaType
				+ ", attachmentFile=" + attachmentFile + "]";
	}

}
