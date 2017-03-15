package com.sap.csc.poc.ems.model.jpa.entitlement;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.sap.csc.poc.ems.model.enumeration.EntitlementOperation;
import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class EntitlementItemHistory extends ModificatoryEntity {

	private static final long serialVersionUID = 7245770499434808649L;

	@Id
	@GeneratedValue
	private long id;

	@Enumerated(EnumType.STRING)
	private EntitlementOperation operation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EntitlementOperation getOperation() {
		return operation;
	}

	public void setOperation(EntitlementOperation operation) {
		this.operation = operation;
	}
}
