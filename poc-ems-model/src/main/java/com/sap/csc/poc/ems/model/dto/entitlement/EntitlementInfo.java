package com.sap.csc.poc.ems.model.dto.entitlement;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.beans.BeanUtils;

import com.sap.csc.poc.ems.model.enumeration.DocumentType;
import com.sap.csc.poc.ems.model.enumeration.EntitlementStatus;
import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHeader;

public class EntitlementInfo implements Serializable {

	private static final long serialVersionUID = -6366357506735351800L;

	private long id;

	private String title;
	private String description;
	private Calendar beginPostingDate;
	private Calendar endPostingDate;
	private String customerID;
	private String customerName;
	private String documentID;
	private DocumentType documentType;
	private Calendar postingDate;
	private EntitlementStatus status;

	public EntitlementInfo() {
	}

	public EntitlementInfo(EntitlementHeader entitlement) {
		if (entitlement == null) {
			return;
		}
		BeanUtils.copyProperties(entitlement, this, "items", "histories");
	}

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
}
