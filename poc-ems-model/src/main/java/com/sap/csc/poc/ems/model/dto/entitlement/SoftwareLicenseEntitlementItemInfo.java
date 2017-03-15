package com.sap.csc.poc.ems.model.dto.entitlement;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.sap.csc.poc.ems.model.enumeration.EntitlementStatus;
import com.sap.csc.poc.ems.model.jpa.common.Validity;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementItem;

public class SoftwareLicenseEntitlementItemInfo implements Serializable {

	private static final long serialVersionUID = -6366357506735351800L;

	private long id;
	private String licenseKey;
	protected Validity validity;
	private EntitlementStatus status;

	public SoftwareLicenseEntitlementItemInfo() {
	}

	public SoftwareLicenseEntitlementItemInfo(SoftwareLicenseEntitlementItem item) {
		if (item == null) {
			return;
		}
		BeanUtils.copyProperties(item, this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public Validity getValidity() {
		return validity;
	}

	public void setValidity(Validity validity) {
		this.validity = validity;
	}

	public EntitlementStatus getStatus() {
		return status;
	}

	public void setStatus(EntitlementStatus status) {
		this.status = status;
	}
}
