package com.sap.csc.poc.ems.service.brm.dto.vocabulary;

import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.service.brm.dto.metadata.BrmSuffix;

/**
 * @author Vincent Chen
 *
 */
@Component
public class BrmVocabularySuffix implements BrmSuffix {

	@Override
	public String getSuffix() {
		return "vocabulary";
	}

}
