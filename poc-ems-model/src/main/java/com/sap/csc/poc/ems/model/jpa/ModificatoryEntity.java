package com.sap.csc.poc.ems.model.jpa;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author I071053
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ModificatoryEntity implements Serializable {

	protected static final long serialVersionUID = -5083840345440733970L;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	protected Calendar createOn;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	protected Calendar updateOn;

	@JsonIgnore
	protected boolean active = true;

	public Calendar getCreateOn() {
		return this.createOn;
	}

	protected void setCreateOn(Calendar createOn) {
		this.createOn = createOn;
	}

	public Calendar getUpdateOn() {
		return this.updateOn;
	}

	protected void setUpdateOn(Calendar updateOn) {
		this.updateOn = updateOn;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
