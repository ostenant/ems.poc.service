package com.sap.csc.poc.ems.service.brm.rest.alpha.basic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.service.brm.client.BrmExecutionClient;
import com.sap.csc.poc.ems.service.brm.config.web.HttpApiService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmExecutionService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Vincent Chen
 *
 */
@RestController
@RequestMapping("basic")
public class BrmExecutionServiceImpl extends HttpApiService implements BrmExecutionService {

	@Autowired
	private BrmExecutionClient brmExecutionClient;

	@ApiOperation(value = "Invoke given rule artifact with provided conditions and retrive result", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ruleName", paramType = "query", value = "Qualified rule artifact name. For example, \"pkg1.pkg2::rule_name\"", required = true, dataType = "String"),
			@ApiImplicitParam(name = "executionBody", paramType = "body", value = "Query conditions for invoke rules", required = true, dataType = "JsonArray") })
	@RequestMapping(value = "rule/execution", method = RequestMethod.POST)
	public String execute(@RequestParam("ruleName") String ruleName, @RequestBody String executionBody) {
		return brmExecutionClient.invoke(ruleName, executionBody);
	}
}
