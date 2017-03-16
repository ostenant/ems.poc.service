package com.sap.csc.poc.ems.service.brm.rest.alpha.basic;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Vincent Chen
 *
 */
@FeignClient(name = "business-rule-service")
public interface BrmExecutionService {

	/**
	 * Invoke rule with given conditions
	 * 
	 * @param ruleName
	 * @param executionBody
	 * @return
	 */
	String execute(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody);

}
