package com.sap.csc.poc.ems.model.jpa.entitlement;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.sap.csc.poc.ems.model.enumeration.EntitlementOperation;
import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EntitlementHistory extends ModificatoryEntity {

	private static final long serialVersionUID = -826226835295665203L;

	@Id
	@GeneratedValue
	private long id;

	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private EntitlementOperation operation;

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
