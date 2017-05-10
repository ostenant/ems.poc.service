package com.sap.csc.poc.ems.service.brm.rest.alpha.basic.fallback;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;

import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmDecisionTableService;
import com.sap.csc.poc.ems.service.brm.rest.exception.FeignFallbackStatus;

public class BrmDecisionTableServiceFallback implements BrmDecisionTableService {

	@Override
	public String retrieve(String name) {
		return FeignFallbackStatus.ERROR_MESSAGE;
	}

	@Override
	public Pair<HttpStatus, String> create(String body) {
		return FeignFallbackStatus.ERROR_PAIR;
	}

	@Override
	public Pair<HttpStatus, String> update(String body) {
		return FeignFallbackStatus.ERROR_PAIR;
	}

	@Override
	public Pair<HttpStatus, String> delete(String name) {
		return FeignFallbackStatus.ERROR_PAIR;
	}

	@Override
	public Pair<HttpStatus, String> activate(String name) {
		return FeignFallbackStatus.ERROR_PAIR;
	}

}
