package com.sap.csc.poc.ems.model.jpa.common;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Validity implements Serializable {

	private static final long serialVersionUID = 7245770499434808649L;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar validityFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar validityTo;

	public Calendar getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(Calendar validityFrom) {
		this.validityFrom = validityFrom;
	}

	public Calendar getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(Calendar validityTo) {
		this.validityTo = validityTo;
	}

}
