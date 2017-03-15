package com.sap.csc.poc.ems.model.dto.entitlement;

import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeader;

public class SoftwareLicenseEntitlementInfo extends EntitlementInfo {

	private static final long serialVersionUID = -6366357506735351800L;

	public SoftwareLicenseEntitlementInfo() {
		super();
	}

	public SoftwareLicenseEntitlementInfo(SoftwareLicenseEntitlementHeader entitlement) {
		super(entitlement);
	}
}
