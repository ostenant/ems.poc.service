package com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHeader;

@Entity
public class SoftwareLicenseEntitlementHeader extends EntitlementHeader {

	private static final long serialVersionUID = -8143344673350394403L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "header")
	private List<SoftwareLicenseEntitlementItem> items;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "source")
	private List<SoftwareLicenseEntitlementHeaderHistory> histories;

	public List<SoftwareLicenseEntitlementItem> getItems() {
		return items;
	}

	public void setItems(List<SoftwareLicenseEntitlementItem> items) {
		this.items = items;
	}

	public List<SoftwareLicenseEntitlementHeaderHistory> getHistories() {
		return histories;
	}

	public void setHistories(List<SoftwareLicenseEntitlementHeaderHistory> histories) {
		this.histories = histories;
	}
}
