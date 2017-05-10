package com.sap.csc.poc.ems.service.brm.rest.alpha.composite.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.service.brm.config.web.HttpApiService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmDecisionTableService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmExecutionService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmVocabularyService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.composite.BrmCompositeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Vincent Chen
 *
 */
@RestController
@RequestMapping("composite")
public class BrmCompositeServiceImpl extends HttpApiService implements BrmCompositeService {

	@Autowired
	private BrmVocabularyService brmVocabularyService;

	@Autowired
	private BrmDecisionTableService brmDecisionTableService;

	@Autowired
	private BrmExecutionService brmExecutionService;

	@ApiOperation(value = "Creates and activates vocabulary", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vocabularyBody", paramType = "body", value = "Vocabulary content", required = true, dataType = "JsonArray") })
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateVocabulary(@RequestBody String vocabularyBody) {
		Pair<HttpStatus, String> httpPair = brmVocabularyService.create(vocabularyBody);
		if (HttpStatus.CREATED == httpPair.getLeft()) {
			return brmVocabularyService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@ApiOperation(value = "Updates and activates vocabulary", httpMethod = "PUT")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vocabularyBody", paramType = "body", value = "Vocabulary content", required = true, dataType = "JsonArray") })
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.PUT)
	public Pair<HttpStatus, String> updateAndActivateVocabulary(@RequestBody String vocabularyBody) {
		Pair<HttpStatus, String> httpPair = brmVocabularyService.update(vocabularyBody);
		if (HttpStatus.NO_CONTENT == httpPair.getLeft()) {
			return brmVocabularyService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@ApiOperation(value = "Creates and activates decisionTable", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "decisionTableBody", paramType = "body", value = "DecisionTable content", required = true, dataType = "JsonArray") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateDecisionTable(@RequestBody String decisionTableBody) {
		Pair<HttpStatus, String> httpPair = brmDecisionTableService.create(decisionTableBody);
		if (HttpStatus.CREATED == httpPair.getLeft()) {
			return brmDecisionTableService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@ApiOperation(value = "Updates and activates decisionTable", httpMethod = "PUT")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "decisionTableBody", paramType = "body", value = "DecisionTable content", required = true, dataType = "JsonArray") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.PUT)
	public Pair<HttpStatus, String> updateAndActivateDecisionTable(@RequestBody String decisionTableBody) {
		Pair<HttpStatus, String> httpPair = brmDecisionTableService.update(decisionTableBody);
		if (HttpStatus.NO_CONTENT == httpPair.getLeft()) {
			return brmDecisionTableService.activate(httpPair.getRight().toLowerCase().trim());
		} else {
			return httpPair;
		}
	}

	@ApiOperation(value = "Executes specified rule", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ruleName", paramType = "query", value = "Target rule", required = true, dataType = "JsonArray"),
			@ApiImplicitParam(name = "executionBody", paramType = "body", value = "Conditions combination", required = true, dataType = "JsonArray") })
	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	public String invoke(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody) {
		return brmExecutionService.execute(ruleName, executionBody);
	}

}
