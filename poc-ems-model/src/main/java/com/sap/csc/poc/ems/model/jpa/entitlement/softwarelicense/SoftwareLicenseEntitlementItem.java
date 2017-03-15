package com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementItem;

@Entity
public class SoftwareLicenseEntitlementItem extends EntitlementItem {

	private static final long serialVersionUID = 7245770499434808649L;

	private String licenseKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private SoftwareLicenseEntitlementHeader header;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "source")
	private List<SoftwareLicenseEntitlementItemHistory> histories;

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public SoftwareLicenseEntitlementHeader getHeader() {
		return header;
	}

	public void setHeader(SoftwareLicenseEntitlementHeader header) {
		this.header = header;
	}

	public List<SoftwareLicenseEntitlementItemHistory> getHistories() {
		return histories;
	}

	public void setHistories(List<SoftwareLicenseEntitlementItemHistory> histories) {
		this.histories = histories;
	}
}
