package com.sap.csc.poc.ems.service.brm.rest.alpha.basic.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.gateway.web.HttpApiService;
import com.sap.csc.poc.ems.service.brm.rest.alpha.basic.BrmHistoryService;

/**
 * @author Vincent Chen
 *
 */
@RestController
@RequestMapping("basic")
public class BrmHistoryServiceImpl extends HttpApiService implements BrmHistoryService {

}
