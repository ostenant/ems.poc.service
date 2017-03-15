package com.sap.csc.poc.ems.model.jpa.entitlement;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EntitlementHeaderValue extends EntitlementValue {

	private static final long serialVersionUID = -8143344673350394403L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private EntitlementHeader header;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private EntitlementHeaderAttribute attribute;

	public EntitlementHeader getHeader() {
		return header;
	}

	public void setHeader(EntitlementHeader header) {
		this.header = header;
	}

	public EntitlementHeaderAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(EntitlementHeaderAttribute attribute) {
		this.attribute = attribute;
	}

}
