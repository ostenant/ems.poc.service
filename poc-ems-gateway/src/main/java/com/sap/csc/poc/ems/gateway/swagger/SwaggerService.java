package com.sap.csc.poc.ems.gateway.swagger;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sap.csc.poc.ems.gateway.web.HttpApiService;

@Controller
public class SwaggerService extends HttpApiService {

	@GetMapping("/")
	public void index() throws IOException {
		super.getHttpServletResponse().sendRedirect("/swagger-ui.html");
	}
}
