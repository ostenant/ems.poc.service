package com.sap.csc.poc.ems.service.contract.entitlement;

import org.springframework.cloud.netflix.feign.FeignClient;

import io.swagger.annotations.Api;

@FeignClient(name = "admin-service")
@Api(value = EntitlementTypeService.SERVICE_NAME)
public interface EntitlementTypeService {

	static final String SERVICE_NAME = "Entitlement Type Service API";

}
