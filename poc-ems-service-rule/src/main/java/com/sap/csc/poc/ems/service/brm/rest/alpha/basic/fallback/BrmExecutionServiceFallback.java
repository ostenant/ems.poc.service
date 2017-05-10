package com.sap.csc.poc.ems.service.brm.rest.alpha.basic.fallback;

import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmExecutionService;
import com.sap.csc.poc.ems.service.brm.rest.exception.FeignFallbackStatus;

public class BrmExecutionServiceFallback implements BrmExecutionService {

	@Override
	public String execute(String ruleName, String executionBody) {
		return FeignFallbackStatus.ERROR_MESSAGE;
	}

}
