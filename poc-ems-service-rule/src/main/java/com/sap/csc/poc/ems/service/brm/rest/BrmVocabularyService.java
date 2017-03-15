package com.sap.csc.poc.ems.service.brm.rest;

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

@FeignClient(name = "business-rule-service")
public interface BrmVocabularyService {

	@ApiOperation(value = "Returns specified vocabulary with entire name. pkg_id::obejct_name", httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", required = true, dataType = "String") })
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.GET)
	String retrieve(@RequestParam("name") String name);

	@ApiOperation(value = "Creates vocabulary with the given body", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "body", paramType = "body", required = true, dataType = "String") })
	@RequestMapping(value = "rule/vocabulary", method = RequestMethod.POST)
	Pair<HttpStatus, String> create(@RequestBody String body);

	@ApiOperation(value = "Activates specified vocabulary with entire name. pkg_id::obejct_name", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", required = true, dataType = "String") })
	@RequestMapping(value = "rule/vocabulary/activate", method = RequestMethod.POST)
	Pair<HttpStatus, String> activate(@RequestParam("name") String name);

}
