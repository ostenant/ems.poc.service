package com.sap.csc.poc.ems.service.brm.rest.alpha.composite.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sap.csc.poc.ems.service.brm.config.web.HttpApiService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmDecisionTableService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmExecutionService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmVocabularyService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.composite.BrmCompositeService;

/**
 * @author Vincent Chen
 *
 */
@Component
public class BrmCompositeServiceImpl extends HttpApiService implements BrmCompositeService {

	@Autowired
	private BrmVocabularyService brmVocabularyService;

	@Autowired
	private BrmDecisionTableService brmDecisionTableService;

	@Autowired
	private BrmExecutionService brmExecutionService;

	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateVocabulary(@RequestBody String vocabularyBody) {
		Pair<HttpStatus, String> httpPair = brmVocabularyService.create(vocabularyBody);
		if (HttpStatus.CREATED == httpPair.getLeft()) {
			return brmVocabularyService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.PUT)
	public Pair<HttpStatus, String> updateAndActivateVocabulary(@RequestBody String vocabularyBody) {
		Pair<HttpStatus, String> httpPair = brmVocabularyService.update(vocabularyBody);
		if (HttpStatus.NO_CONTENT == httpPair.getLeft()) {
			return brmVocabularyService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateDecisionTable(@RequestBody String decisionTableBody) {
		Pair<HttpStatus, String> httpPair = brmDecisionTableService.create(decisionTableBody);
		if (HttpStatus.CREATED == httpPair.getLeft()) {
			return brmDecisionTableService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.PUT)
	public Pair<HttpStatus, String> updateAndActivateDecisionTable(@RequestBody String decisionTableBody) {
		Pair<HttpStatus, String> httpPair = brmDecisionTableService.update(decisionTableBody);
		if (HttpStatus.NO_CONTENT == httpPair.getLeft()) {
			return brmDecisionTableService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	public String invoke(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody) {
		// TODO
		return brmExecutionService.execute(ruleName, executionBody);
	}

}
