package com.sap.csc.poc.ems.model.dto.bre.decisiontable;

import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.dto.bre.metadata.BreSuffix;

@Component
public class BreDecisionTableSuffix implements BreSuffix {

	@Override
	public String getSuffix() {
		return "decisiontable";
	}

}
