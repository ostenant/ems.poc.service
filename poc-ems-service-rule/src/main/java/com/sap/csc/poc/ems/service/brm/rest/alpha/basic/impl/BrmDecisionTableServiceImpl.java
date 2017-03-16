package com.sap.csc.poc.ems.service.brm.rest.alpha.basic.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.csc.poc.ems.service.brm.client.BrmRepositoryClient;
import com.sap.csc.poc.ems.service.brm.config.web.HttpApiService;
import com.sap.csc.poc.ems.service.brm.dto.decisiontable.BrmDecisionTable;
import com.sap.csc.poc.ems.service.brm.dto.decisiontable.BrmDecisionTableSuffix;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmDecisionTableService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Vincent Chen
 *
 */
@RestController
@RequestMapping("basic")
public class BrmDecisionTableServiceImpl extends HttpApiService implements BrmDecisionTableService {

	@Autowired
	private BrmDecisionTableSuffix brmDecisionTableSuffix;

	@Autowired
	private BrmRepositoryClient brmRepositoryClient;

	@Autowired
	private ObjectMapper objectMapper;

	@ApiOperation(value = "Returns specified decisiontable with entire name. pkg_id::object_name", httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.GET)
	public String retrieve(@RequestParam("name") String name) {
		return brmRepositoryClient.retrieve(name, brmDecisionTableSuffix.getSuffix());
	}

	@ApiOperation(value = "Creates decisiontable with the given body", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "body", paramType = "body", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.POST)
	public Pair<HttpStatus, String> create(@RequestBody String body) {
		try {
			BrmDecisionTable decisionTable = objectMapper.readValue(body, BrmDecisionTable.class);
			return brmRepositoryClient.create(decisionTable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@ApiOperation(value = "Updates existed decisiontable with the given body", httpMethod = "PUT")
	@ApiImplicitParams({ @ApiImplicitParam(name = "body", paramType = "body", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.PUT)
	public Pair<HttpStatus, String> update(@RequestBody String body) {
		try {
			BrmDecisionTable decisionTable = objectMapper.readValue(body, BrmDecisionTable.class);
			return brmRepositoryClient.update(decisionTable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@ApiOperation(value = "Activates specified decisiontable with entire name. pkg_id::object_name", httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable/activate", method = RequestMethod.POST)
	public Pair<HttpStatus, String> activate(@RequestParam("name") String name) {
		return brmRepositoryClient.activate(name, brmDecisionTableSuffix.getSuffix());
	}

	@ApiOperation(value = "Deletes specified decisiontable with entire name. pkg_id::object_name", httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", required = true, dataType = "String") })
	@RequestMapping(value = "rule/decisionTable", method = RequestMethod.DELETE)
	public Pair<HttpStatus, String> delete(@RequestParam("name") String name) {
		try {
			return brmRepositoryClient.delete(name, brmDecisionTableSuffix.getSuffix());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
