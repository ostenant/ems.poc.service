package com.sap.csc.poc.ems.service.contract.notification;

import org.springframework.cloud.netflix.feign.FeignClient;

import io.swagger.annotations.Api;

@FeignClient(name = "admin-service")
@Api(value = NotificationService.SERVICE_NAME)
public interface NotificationService {

	static final String SERVICE_NAME = "Pull notification API";
}
