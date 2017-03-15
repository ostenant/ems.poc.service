package com.sap.csc.poc.ems.integration.erp.inbound;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.gateway.web.HttpApiService;
import com.sap.csc.poc.ems.service.contract.bre.BreDecisionTableService;
import com.sap.csc.poc.ems.service.contract.bre.BreExecutionService;
import com.sap.csc.poc.ems.service.contract.bre.BreVocabularyService;

@RestController
public class ERPIntegrationService extends HttpApiService {

	@Autowired
	private BreVocabularyService breVocabularyService;

	@Autowired
	private BreDecisionTableService breDecisionTableService;

	@Autowired
	private BreExecutionService breExecutionService;

	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.GET)
	public String retrieveVocabulary(@RequestParam("name") String name) {
		return breVocabularyService.retrieve(name);
	}

	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateVocabulary(@RequestBody String vocabularyBody) {
		Pair<HttpStatus, String> httpPair = breVocabularyService.create(vocabularyBody);
		if (HttpStatus.CREATED == httpPair.getLeft()) {
			return breVocabularyService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.GET)
	public String retrieveDecisionTable(@RequestParam("name") String name) {
		return breDecisionTableService.retrieve(name);
	}

	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateDecisionTable(@RequestBody String decisionTableBody) {
		Pair<HttpStatus, String> httpPair = breDecisionTableService.create(decisionTableBody);
		if (HttpStatus.CREATED == httpPair.getLeft()) {
			return breDecisionTableService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	public String invoke(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody) {
		return breExecutionService.execute(ruleName, executionBody);
	}
}
