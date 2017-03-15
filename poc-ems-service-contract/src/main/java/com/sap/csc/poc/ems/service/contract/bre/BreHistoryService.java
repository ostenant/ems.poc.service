package com.sap.csc.poc.ems.service.contract.bre;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "rule-engine-service")
public interface BreHistoryService {

}
