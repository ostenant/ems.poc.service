package com.sap.csc.poc.ems.service.user;

import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.poc.ems.gateway.web.HttpApiService;
import com.sap.csc.poc.ems.service.contract.user.UserService;

@RestController
public class UserServiceImpl extends HttpApiService implements UserService {

}
