package com.sap.csc.poc.ems.service.job;

import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.gateway.web.HttpApiService;
import com.sap.csc.poc.ems.service.contract.job.ValidityCheckService;

@RestController
public class ValidityCheckServiceImpl extends HttpApiService implements ValidityCheckService {

}
