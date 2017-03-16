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

	@ApiOperation(value = "Invoke given rule artifact with provided conditions and retrive result", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ruleName", paramType = "query", value = "Qualified rule artifact name. For example, \"pkg1.pkg2::rule_name\"", required = true, dataType = "String"),
			@ApiImplicitParam(name = "executionBody", paramType = "body", value = "Query conditions for invoke rules", required = true, dataType = "JsonArray") })
	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	String execute(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody);

}
