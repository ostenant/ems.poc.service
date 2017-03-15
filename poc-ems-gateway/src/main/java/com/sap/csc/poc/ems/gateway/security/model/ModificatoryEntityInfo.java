package com.sap.csc.poc.ems.gateway.security.model;

import java.io.Serializable;
import java.util.Calendar;

import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;

public abstract class ModificatoryEntityInfo implements Serializable {

	private static final long serialVersionUID = -4288429348716518318L;

	protected Calendar createOn;

	protected Calendar updateOn;

	public ModificatoryEntityInfo() {
	}

	public ModificatoryEntityInfo(ModificatoryEntity modificatoryEntity) {
		if (modificatoryEntity == null) {
			return;
		}
		this.setCreateOn(modificatoryEntity.getCreateOn());
		this.setUpdateOn(modificatoryEntity.getUpdateOn());
	}

	public Calendar getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Calendar createOn) {
		this.createOn = createOn;
	}

	public Calendar getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Calendar updateOn) {
		this.updateOn = updateOn;
	}
}
