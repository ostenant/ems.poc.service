package com.sap.csc.poc.ems.service.brm.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sap.csc.poc.ems.ProjectRoot;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// API Info
				.apiInfo(apiInfo()).select()
				// Only API under Project Root Package
				.apis(RequestHandlerSelectors.basePackage(ProjectRoot.class.getPackage().getName()))
				// API Operation Only
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// All Paths
				.paths(PathSelectors.any())
				// Build
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SAP business rule service api wrapper")
				.description("SAP business rule service api wrapper layer")//
				.termsOfServiceUrl(
						"https://uacp2.hana.ondemand.com/http.svc/rc/DRAFT/business_rules_repository_api/Cloud/en-US/business-rules-repository-api.html")//
				.version("2.0")//
				.build();
	}

}
