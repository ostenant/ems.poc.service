package com.sap.csc.poc.ems.service.brm.rest;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "business-rule-service")
public interface BrmHistoryService {

}
