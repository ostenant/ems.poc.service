package com.sap.csc.poc.ems.service.brm.rest.alpha.composite.fallback;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;

import com.sap.csc.poc.ems.service.brm.rest.alpha.composite.BrmCompositeService;
import com.sap.csc.poc.ems.service.brm.rest.exception.FeignFallbackStatus;

public class BrmCompositeServiceFallback implements BrmCompositeService {
	

	@Override
	public Pair<HttpStatus, String> createAndActivateVocabulary(String vocabularyBody) {
		return FeignFallbackStatus.ERROR_PAIR;
	}

	@Override
	public Pair<HttpStatus, String> updateAndActivateVocabulary(String vocabularyBody) {
		return  FeignFallbackStatus.ERROR_PAIR;
	}

	@Override
	public Pair<HttpStatus, String> createAndActivateDecisionTable(String decisionTableBody) {
		return FeignFallbackStatus.ERROR_PAIR;
	}

	@Override
	public Pair<HttpStatus, String> updateAndActivateDecisionTable(String decisionTableBody) {
		return FeignFallbackStatus.ERROR_PAIR;
	}

	@Override
	public String invoke(String ruleName, String executionBody) {
		return FeignFallbackStatus.ERROR_MESSAGE;
	}

}
