package com.sap.csc.poc.ems.service.brm.rest.alpha.basic;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.fallback.BrmExecutionServiceFallback;

/**
 * @author Vincent Chen
 *
 */
@FeignClient(name = "business-rule-service", fallback = BrmExecutionServiceFallback.class)
@RequestMapping("basic")
public interface BrmExecutionService {

	/**
	 * Invoke rule with given conditions
	 * 
	 * @param ruleName
	 * @param executionBody
	 * @return
	 */
	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	String execute(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody);

}
