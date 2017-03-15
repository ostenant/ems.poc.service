package com.sap.csc.poc.ems.model.dto.bre;

import org.springframework.stereotype.Component;

@Component
public class BreVocabulary implements BreMetadata {

	@Override
	public String getSuffix() {
		return "vocabulary";
	}

}
