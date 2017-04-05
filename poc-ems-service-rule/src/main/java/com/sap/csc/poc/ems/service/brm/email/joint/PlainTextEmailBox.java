package com.sap.csc.poc.ems.service.brm.email.joint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlainTextEmailBox implements Serializable {

	private static final long serialVersionUID = -5474202094868757525L;

	public static final String DEFAULT_ENCCODING = "UTF-8";

	/**
	 * Email sender
	 */
	protected Originator originator;

	/**
	 * Email receiver
	 */
	protected List<Conspirator> conspirators = new ArrayList<Conspirator>();

	/**
	 * Email subject
	 */
	protected String subject;

	/**
	 * Email body
	 */
	protected String body;

	/**
	 * Email charset
	 */
	protected String charset = DEFAULT_ENCCODING;

	public Originator getOriginator() {
		return originator;
	}

	public void setOriginator(Originator originator) {
		this.originator = originator;
	}

	public List<Conspirator> getConspirators() {
		return conspirators;
	}

	public void setConspirators(List<Conspirator> conspirators) {
		this.conspirators = conspirators;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public String toString() {
		return "PlainTextEmailBox [originator=" + originator + ", conspirators=" + conspirators + ", subject=" + subject
				+ ", body=" + body + ", charset=" + charset + "]";
	}

}
