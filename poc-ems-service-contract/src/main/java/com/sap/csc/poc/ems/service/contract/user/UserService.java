package com.sap.csc.poc.ems.service.contract.user;

import org.springframework.cloud.netflix.feign.FeignClient;

import io.swagger.annotations.Api;

@FeignClient(name = "user-service")
@Api(value = UserService.SERVICE_NAME)
public interface UserService {

	static final String SERVICE_NAME = "User Detail API";

}
