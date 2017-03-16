package com.sap.csc.poc.ems.service.brm.config.serialization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Vincent Chen
 *
 */
@Configuration
public class BrmSerializationConfig {

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}

}
