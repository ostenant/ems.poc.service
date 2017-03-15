package com.sap.csc.poc.ems.service.bre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.gateway.web.HttpApiService;
import com.sap.csc.poc.ems.service.bre.component.BreInvokerComponent;
import com.sap.csc.poc.ems.service.contract.bre.BreExecutionService;

@RestController
public class BreExecutionServiceImpl extends HttpApiService implements BreExecutionService {

	@Autowired
	private BreInvokerComponent breInvokerComponent;

	@Override
	public String execute(String ruleName, String executionBody) {
		return breInvokerComponent.invoke(ruleName, executionBody);
	}
}
