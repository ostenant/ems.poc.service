package com.sap.csc.poc.ems.model.jpa.entitlement;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.sap.csc.poc.ems.model.enumeration.EntitlementStatus;
import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;
import com.sap.csc.poc.ems.model.jpa.common.Validity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class EntitlementItem extends ModificatoryEntity {

	private static final long serialVersionUID = -826226835295665203L;

	@Id
	@GeneratedValue
	private long id;

	protected Validity validity;

	@Enumerated(EnumType.STRING)
	private EntitlementStatus status;

	@OneToMany(cascade = CascadeType.ALL)
	private List<EntitlementItemValue> extensions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<EntitlementItemValue> getExtensions() {
		return extensions;
	}

	public void setExtensions(List<EntitlementItemValue> extensions) {
		this.extensions = extensions;
	}
}
