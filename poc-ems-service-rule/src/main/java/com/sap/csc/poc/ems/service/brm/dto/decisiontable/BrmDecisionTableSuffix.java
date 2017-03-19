package com.sap.csc.poc.ems.service.brm.dto.decisiontable;

import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.service.brm.dto.metadata.BrmSuffix;

/**
 * @author Vincent Chen
 *
 */
@Component
public class BrmDecisionTableSuffix implements BrmSuffix {

	@Override
	public String getSuffix() {
		return "decisiontable";
	}

}
