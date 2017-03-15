package com.sap.csc.poc.ems.service.bre.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class BreSerializationConfig {

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		om.enable(SerializationFeature.INDENT_OUTPUT);
		om.setDefaultPrettyPrinter(new MinimalPrettyPrinter());
		return om;
	}

}
