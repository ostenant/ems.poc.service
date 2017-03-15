package com.sap.csc.poc.ems.model.jpa.notification;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.sap.csc.poc.ems.model.enumeration.NotificationPriority;
import com.sap.csc.poc.ems.model.enumeration.NotificationStatus;
import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Notification extends ModificatoryEntity {

	private static final long serialVersionUID = 1109222046140199741L;

	@Id
	@GeneratedValue
	private long id;

	private String subject;
	private String description;
	private long referenceId;
	private String referenceType;
	private long senderId;

	@Enumerated(EnumType.STRING)
	private NotificationPriority priority;

	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(long referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public NotificationPriority getPriority() {
		return priority;
	}

	public void setPriority(NotificationPriority priority) {
		this.priority = priority;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
}
