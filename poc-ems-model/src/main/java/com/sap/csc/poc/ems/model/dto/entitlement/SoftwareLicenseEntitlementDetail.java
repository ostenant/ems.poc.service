package com.sap.csc.poc.ems.model.dto.entitlement;

import java.util.List;
import java.util.stream.Collectors;

import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeader;

public class SoftwareLicenseEntitlementDetail extends SoftwareLicenseEntitlementInfo {

	private static final long serialVersionUID = -6366357506735351800L;

	private List<SoftwareLicenseEntitlementItemInfo> items;
	private List<SoftwareLicenseEntitlementHistoryInfo> histories;

	public SoftwareLicenseEntitlementDetail() {
	}

	public SoftwareLicenseEntitlementDetail(SoftwareLicenseEntitlementHeader entitlement) {
		super(entitlement);
		if (entitlement == null) {
			return;
		}
		this.setItems(entitlement.getItems().stream().map(item -> new SoftwareLicenseEntitlementItemInfo(item)).collect(Collectors.toList()));
		this.setHistories(entitlement.getHistories().stream().map(history -> new SoftwareLicenseEntitlementHistoryInfo(history)).collect(Collectors
			.toList()));
	}

	public List<SoftwareLicenseEntitlementItemInfo> getItems() {
		return items;
	}

	public void setItems(List<SoftwareLicenseEntitlementItemInfo> items) {
		this.items = items;
	}

	public List<SoftwareLicenseEntitlementHistoryInfo> getHistories() {
		return histories;
	}

	public void setHistories(List<SoftwareLicenseEntitlementHistoryInfo> histories) {
		this.histories = histories;
	}

}
