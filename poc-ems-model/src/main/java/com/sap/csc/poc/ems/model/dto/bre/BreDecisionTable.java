package com.sap.csc.poc.ems.model.dto.bre;

import org.springframework.stereotype.Component;

@Component
public class BreDecisionTable implements BreMetadata {

	@Override
	public String getSuffix() {
		return "decisiontable";
	}

}
