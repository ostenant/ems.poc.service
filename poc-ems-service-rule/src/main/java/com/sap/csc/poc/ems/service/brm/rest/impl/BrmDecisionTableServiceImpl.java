package com.sap.csc.poc.ems.service.brm.rest.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.csc.poc.ems.service.brm.client.BrmRepositoryClient;
import com.sap.csc.poc.ems.service.brm.config.web.HttpApiService;
import com.sap.csc.poc.ems.service.brm.dto.decisiontable.BrmDecisionTable;
import com.sap.csc.poc.ems.service.brm.dto.decisiontable.BrmDecisionTableSuffix;
import com.sap.csc.poc.ems.service.brm.rest.BrmDecisionTableService;

@RestController
public class BrmDecisionTableServiceImpl extends HttpApiService implements BrmDecisionTableService {

	@Autowired
	private BrmDecisionTableSuffix brmDecisionTableSuffix;

	@Autowired
	private BrmRepositoryClient brmRepositoryClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String retrieve(String name) {
		return brmRepositoryClient.retrieve(name, brmDecisionTableSuffix.getSuffix());
	}

	@Override
	public Pair<HttpStatus, String> create(@RequestBody String body) {
		try {
			BrmDecisionTable decisionTable = objectMapper.readValue(body, BrmDecisionTable.class);
			return brmRepositoryClient.create(decisionTable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Pair<HttpStatus, String> activate(String name) {
		return brmRepositoryClient.activate(name, brmDecisionTableSuffix.getSuffix());
	}

}
