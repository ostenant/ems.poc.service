package com.sap.csc.poc.ems.model.jpa.entitlement;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class EntitlementHeaderAttribute extends EntitlementAttribute {

	private static final long serialVersionUID = -8143344673350394403L;

	@ManyToOne(fetch = FetchType.LAZY)
	private EntitlementType entitlementType;

	public EntitlementType getEntitlementType() {
		return entitlementType;
	}

	public void setEntitlementType(EntitlementType entitlementType) {
		this.entitlementType = entitlementType;
	}

}
