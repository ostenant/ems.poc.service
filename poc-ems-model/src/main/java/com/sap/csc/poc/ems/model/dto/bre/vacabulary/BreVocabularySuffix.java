package com.sap.csc.poc.ems.model.dto.bre.vacabulary;

import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.dto.bre.metadata.BreSuffix;

@Component
public class BreVocabularySuffix implements BreSuffix {

	@Override
	public String getSuffix() {
		return "vocabulary";
	}

}
