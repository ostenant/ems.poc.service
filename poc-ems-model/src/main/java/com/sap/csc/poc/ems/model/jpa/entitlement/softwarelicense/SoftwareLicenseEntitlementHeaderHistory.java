package com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHistory;

@Entity
public class SoftwareLicenseEntitlementHeaderHistory extends EntitlementHistory {

	private static final long serialVersionUID = 7245770499434808649L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private SoftwareLicenseEntitlementHeader source;

	public SoftwareLicenseEntitlementHeader getSource() {
		return source;
	}

	public void setSource(SoftwareLicenseEntitlementHeader source) {
		this.source = source;
	}
}
