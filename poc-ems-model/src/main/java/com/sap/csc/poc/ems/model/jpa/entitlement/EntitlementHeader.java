package com.sap.csc.poc.ems.model.jpa.entitlement;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sap.csc.poc.ems.model.enumeration.DocumentType;
import com.sap.csc.poc.ems.model.enumeration.EntitlementStatus;
import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class EntitlementHeader extends ModificatoryEntity {

	private static final long serialVersionUID = -6227298525566287906L;

	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String description;
	private Calendar beginPostingDate;
	private Calendar endPostingDate;
	private String customerID;
	private String customerName;
	private String documentID;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar postingDate;

	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	@Enumerated(EnumType.STRING)
	private EntitlementStatus status = EntitlementStatus.ACTIVE;

	@ManyToOne
	@JoinColumn
	private EntitlementType type;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "header")
	private List<EntitlementHeaderValue> extensions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getBeginPostingDate() {
		return beginPostingDate;
	}

	public void setBeginPostingDate(Calendar beginPostingDate) {
		this.beginPostingDate = beginPostingDate;
	}

	public Calendar getEndPostingDate() {
		return endPostingDate;
	}

	public void setEndPostingDate(Calendar endPostingDate) {
		this.endPostingDate = endPostingDate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Calendar getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Calendar postingDate) {
		this.postingDate = postingDate;
	}

	public EntitlementStatus getStatus() {
		return status;
	}

	public void setStatus(EntitlementStatus status) {
		this.status = status;
	}

	public EntitlementType getType() {
		return type;
	}

	public void setType(EntitlementType type) {
		this.type = type;
	}

	public List<EntitlementHeaderValue> getExtensions() {
		return extensions;
	}

	public void setExtensions(List<EntitlementHeaderValue> extensions) {
		this.extensions = extensions;
	}
}
