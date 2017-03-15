package com.sap.csc.poc.ems.gateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// Disable CSRF
			.csrf().disable()
			// Disable the frame options
			.headers().frameOptions().disable()
			// Home & Login
			// .and().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated()
			// Disable Authentication
			.and().formLogin().disable();
	}
}
