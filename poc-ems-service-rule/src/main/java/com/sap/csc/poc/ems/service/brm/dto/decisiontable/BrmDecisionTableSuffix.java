package com.sap.csc.poc.ems.service.brm.dto.decisiontable;

import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.dto.bre.metadata.BreSuffix;

/**
 * @author Vincent Chen
 *
 */
@Component
public class BrmDecisionTableSuffix implements BreSuffix {

	@Override
	public String getSuffix() {
		return "decisiontable";
	}

}
