package com.sap.csc.poc.ems.service.brm.dto.metadata;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Vincent Chen
 *
 */
public interface BrmBody extends Serializable {

	@JsonIgnore
	public String getEntireName();

}
