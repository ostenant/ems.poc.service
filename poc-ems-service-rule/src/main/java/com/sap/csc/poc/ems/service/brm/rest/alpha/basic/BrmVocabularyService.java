package com.sap.csc.poc.ems.service.brm.rest.alpha.basic;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Vincent Chen
 *
 */
@FeignClient(name = "business-rule-service")
@RequestMapping("basic")
public interface BrmVocabularyService {

	/**
	 * Retrieve vocabulary
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.GET)
	String retrieve(@RequestParam("name") String name);

	/**
	 * Create vocabulary
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.POST)
	Pair<HttpStatus, String> create(@RequestBody String body);

	/**
	 * Update vocabulary
	 * 
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.PUT)
	Pair<HttpStatus, String> update(@RequestBody String body);

	/**
	 * Delete vocabulary
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.DELETE)
	Pair<HttpStatus, String> delete(@RequestParam("name") String name);

	/**
	 * Activate vocabulary
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "rule/vocabulary/activate", method = RequestMethod.POST)
	Pair<HttpStatus, String> activate(@RequestParam("name") String name);

}
