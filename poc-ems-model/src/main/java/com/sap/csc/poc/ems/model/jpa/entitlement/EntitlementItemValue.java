package com.sap.csc.poc.ems.model.jpa.entitlement;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EntitlementItemValue extends EntitlementValue {

	private static final long serialVersionUID = -8143344673350394403L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private EntitlementItem item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private EntitlementItemAttribute attribute;

	public EntitlementItem getItem() {
		return item;
	}

	public void setItem(EntitlementItem item) {
		this.item = item;
	}

	public EntitlementItemAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(EntitlementItemAttribute attribute) {
		this.attribute = attribute;
	}

}
