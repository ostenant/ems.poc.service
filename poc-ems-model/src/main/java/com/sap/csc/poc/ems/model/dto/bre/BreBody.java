package com.sap.csc.poc.ems.model.dto.bre;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface BreBody extends Serializable {

	@JsonIgnore
	public String getEntireName();

}
