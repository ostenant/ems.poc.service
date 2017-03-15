package com.sap.csc.poc.ems.service.brm.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@FeignClient(name = "business-rule-service")
public interface BrmExecutionService {

	@ApiOperation(value = "Compares the given input with the rule and executes rule", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ruleName", paramType = "query", required = true, dataType = "String"),
			@ApiImplicitParam(name = "executionBody", paramType = "body", required = true, dataType = "String") })
	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	String execute(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody);

}
