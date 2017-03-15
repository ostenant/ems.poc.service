package com.sap.csc.poc.ems.service.contract.bre;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rule-engine-service")
public interface BreExecutionService {

	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	String execute(@RequestParam("ruleName") String ruleName, @RequestBody String breExecutionRequestBody);

}
