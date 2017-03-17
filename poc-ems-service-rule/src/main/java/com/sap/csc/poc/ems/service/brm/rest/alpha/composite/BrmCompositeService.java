package com.sap.csc.poc.ems.service.brm.rest.alpha.composite;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Vincent Chen
 *
 */
// @FeignClient(name = "rule-engine-service")
@RequestMapping("composite")
public interface BrmCompositeService {

	/**
	 * creates and activate vocabulary
	 * 
	 * @param vocabularyBody
	 * @return
	 */
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateVocabulary(String vocabularyBody);

	/**
	 * updates and activate vocabulary
	 * 
	 * @param vocabularyBody
	 * @return
	 */
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.PUT)
	public Pair<HttpStatus, String> updateAndActivateVocabulary(String vocabularyBody);

	/**
	 * creates and activate decisionTable
	 * 
	 * @param decisionTableBody
	 * @return
	 */
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.POST)
	public Pair<HttpStatus, String> createAndActivateDecisionTable(String decisionTableBody);

	/**
	 * updates and activate decisionTable
	 * 
	 * @param decisionTableBody
	 * @return
	 */
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.PUT)
	public Pair<HttpStatus, String> updateAndActivateDecisionTable(String decisionTableBody);

	/**
	 * Invoke higher level
	 * 
	 * @param ruleName
	 * @param executionBody
	 * @return
	 */
	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	public String invoke(String ruleName, String executionBody);

}
