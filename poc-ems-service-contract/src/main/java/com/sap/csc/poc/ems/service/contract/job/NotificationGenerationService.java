package com.sap.csc.poc.ems.service.contract.job;

import org.springframework.cloud.netflix.feign.FeignClient;

import io.swagger.annotations.Api;

@FeignClient(name = "job-service")
@Api(value = NotificationGenerationService.SERVICE_NAME)
public interface NotificationGenerationService {

	static final String SERVICE_NAME = "Regular generate notifications API";

}
