package com.sap.csc.poc.ems.service.brm.rest.alpha.basic;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.fallback.BrmHistoryServiceFallback;

/**
 * @author Vincent Chen
 *
 */
@FeignClient(name = "business-rule-service", fallback = BrmHistoryServiceFallback.class)
@RequestMapping("basic")
public interface BrmHistoryService {

}
