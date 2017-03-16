package com.sap.csc.poc.ems.service.brm.rest.alpha.composite;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Vincent Chen
 *
 */
@FeignClient(name = "rule-engine-service")
public interface BrmCompositeService {

	/**
	 * creates and activate vocabulary
	 * 
	 * @param vocabularyBody
	 * @return
	 */
	public Pair<HttpStatus, String> createAndActivateVocabulary(@RequestBody String vocabularyBody);

	/**
	 * updates and activate vocabulary
	 * 
	 * @param vocabularyBody
	 * @return
	 */
	public Pair<HttpStatus, String> updateAndActivateVocabulary(@RequestBody String vocabularyBody);

	/**
	 * creates and activate decisionTable
	 * 
	 * @param decisionTableBody
	 * @return
	 */
	public Pair<HttpStatus, String> createAndActivateDecisionTable(@RequestBody String decisionTableBody);

	/**
	 * updates and activate decisionTable
	 * 
	 * @param decisionTableBody
	 * @return
	 */
	public Pair<HttpStatus, String> updateAndActivateDecisionTable(@RequestBody String decisionTableBody);

	/**
	 * Invoke higher level
	 * 
	 * @param ruleName
	 * @param executionBody
	 * @return
	 */
	public String invoke(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody);

}
