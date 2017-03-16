package com.sap.csc.poc.ems.service.brm.rest.alpha.basic;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author Vincent Chen
 *
 */
@FeignClient(name = "business-rule-service")
public interface BrmHistoryService {

}
