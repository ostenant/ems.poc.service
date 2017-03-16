package com.sap.csc.poc.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;


@SpringBootApplication
@EnableFeignClients
public class Bootstrap extends SpringBootServletInitializer {

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			// Title
			.title("Admin Service API")
			// Description
			.description("The backend service API for EMS admin")
			// Version
			.version("2.0.0")
			// Build
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Bootstrap.class);
	}
}