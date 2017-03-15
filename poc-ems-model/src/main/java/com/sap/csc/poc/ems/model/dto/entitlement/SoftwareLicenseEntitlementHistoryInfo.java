package com.sap.csc.poc.ems.model.dto.entitlement;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.sap.csc.poc.ems.model.enumeration.EntitlementOperation;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeaderHistory;

public class SoftwareLicenseEntitlementHistoryInfo implements Serializable {

	private static final long serialVersionUID = -6366357506735351800L;

	private long id;

	private BigDecimal amount;
	private EntitlementOperation operation;

	public SoftwareLicenseEntitlementHistoryInfo() {
	}

	public SoftwareLicenseEntitlementHistoryInfo(SoftwareLicenseEntitlementHeaderHistory history) {
		if (history == null) {
			return;
		}
		BeanUtils.copyProperties(history, this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public EntitlementOperation getOperation() {
		return operation;
	}

	public void setOperation(EntitlementOperation operation) {
		this.operation = operation;
	}

}
