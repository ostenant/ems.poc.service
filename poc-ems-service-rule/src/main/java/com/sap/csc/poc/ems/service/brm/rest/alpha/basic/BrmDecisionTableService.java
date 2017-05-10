package com.sap.csc.poc.ems.service.brm.rest.alpha.basic;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.fallback.BrmDecisionTableServiceFallback;

/**
 * @author Vincent Chen
 *
 */
@FeignClient(name = "rule-engine-service", fallback = BrmDecisionTableServiceFallback.class)
@RequestMapping("basic")
public interface BrmDecisionTableService {

	/**
	 * Retrieve decisionTable
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.GET)
	String retrieve(@RequestParam("name") String name);

	/**
	 * Create decisionTable
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.POST)
	Pair<HttpStatus, String> create(@RequestBody String body);

	/**
	 * Update decisionTable
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.PUT)
	Pair<HttpStatus, String> update(@RequestBody String body);

	/**
	 * Delete decisionTable
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.DELETE)
	Pair<HttpStatus, String> delete(@RequestParam("name") String name);

	/**
	 * Activate decisionTable
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "rule/decisionTable/activate", method = RequestMethod.POST)
	Pair<HttpStatus, String> activate(@RequestParam("name") String name);

}
