package com.sap.csc.poc.ems.service.brm.rest.impl;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.service.brm.client.BrmExecutionClient;
import com.sap.csc.poc.ems.service.brm.config.web.HttpApiService;
import com.sap.csc.poc.ems.service.brm.rest.BrmExecutionService;

@RestController
public class BrmExecutionServiceImpl extends HttpApiService implements BrmExecutionService {

	@Autowired
	private BrmExecutionClient brmExecutionClient;

	@Override
	public String execute(String ruleName, JSONArray executionBody) {
		return brmExecutionClient.invoke(ruleName, executionBody.toString());
	}
}
