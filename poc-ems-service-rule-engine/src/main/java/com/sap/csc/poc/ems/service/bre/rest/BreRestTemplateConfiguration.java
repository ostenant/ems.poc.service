package com.sap.csc.poc.ems.service.bre.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import com.sap.csc.poc.ems.service.bre.rest.template.GeneralRestTemplate;

@Configuration
public class BreRestTemplateConfiguration {

	@Bean
	@Primary
	@LoadBalanced
	public RestTemplate restTemplate(@Qualifier("generalRestTemplate") GeneralRestTemplate generalRestTemplate) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(generalRestTemplate.getRequestFactory());
		return restTemplate;
	}
}