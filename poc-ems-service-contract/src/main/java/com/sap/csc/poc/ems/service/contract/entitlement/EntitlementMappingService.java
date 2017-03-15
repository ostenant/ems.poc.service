package com.sap.csc.poc.ems.service.contract.entitlement;

import org.springframework.cloud.netflix.feign.FeignClient;

import io.swagger.annotations.Api;

@FeignClient(name = "admin-service")
@Api(value = EntitlementMappingService.SERVICE_NAME)
public interface EntitlementMappingService {

	static final String SERVICE_NAME = "Entitlement Mapping Service API";
}
