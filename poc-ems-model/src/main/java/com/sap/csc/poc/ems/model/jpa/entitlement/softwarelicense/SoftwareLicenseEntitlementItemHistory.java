package com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementItemHistory;

@Entity
public class SoftwareLicenseEntitlementItemHistory extends EntitlementItemHistory {

	private static final long serialVersionUID = 7245770499434808649L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private SoftwareLicenseEntitlementItem source;

	public SoftwareLicenseEntitlementItem getSource() {
		return source;
	}

	public void setSource(SoftwareLicenseEntitlementItem source) {
		this.source = source;
	}
}
