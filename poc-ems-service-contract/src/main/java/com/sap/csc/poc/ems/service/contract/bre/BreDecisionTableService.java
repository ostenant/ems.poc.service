package com.sap.csc.poc.ems.service.contract.bre;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@FeignClient(name = "rule-engine-service")
public interface BreDecisionTableService {

	@ApiOperation(value = "Returns specified decisiontable with entire name. pkg_id::obejct_name.object_suffix", httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.GET)
	String retrieve(@RequestParam("name") String name);

	@ApiOperation(value = "Creates decisiontable with the given body", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "body", paramType = "body", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.POST)
	Pair<HttpStatus, String> create(@RequestBody String body);

	@ApiOperation(value = "Activates specified decisiontable with entire name. pkg_id::obejct_name.object_suffix", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable/activate", method = RequestMethod.POST)
	Pair<HttpStatus, String> activate(@RequestParam("name") String name);
}
