package com.sap.csc.poc.ems.model.jpa.entitlement;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;

@Entity
public class EntitlementType extends ModificatoryEntity {

	private static final long serialVersionUID = -8143344673350394403L;

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entitlementType")
	private List<EntitlementHeaderAttribute> headerAttributes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entitlementType")
	private List<EntitlementItemAttribute> itemAttributes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EntitlementHeaderAttribute> getHeaderAttributes() {
		return headerAttributes;
	}

	public void setHeaderAttributes(List<EntitlementHeaderAttribute> headerAttributes) {
		this.headerAttributes = headerAttributes;
	}

	public List<EntitlementItemAttribute> getItemAttributes() {
		return itemAttributes;
	}

	public void setItemAttributes(List<EntitlementItemAttribute> itemAttributes) {
		this.itemAttributes = itemAttributes;
	}
}
