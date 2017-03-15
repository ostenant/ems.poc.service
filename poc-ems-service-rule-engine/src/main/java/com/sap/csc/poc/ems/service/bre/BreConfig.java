package com.sap.csc.poc.ems.service.bre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.service.bre.BreConfig.AuthenticationConfig.UriConfig;

/**
 * @author I071053
 */
@Configuration
@Component
@PropertySource("classpath:/bre.properties")
public class BreConfig {

	@Autowired
	protected AuthenticationConfig authentication;

	@Autowired
	protected UriConfig uri;

	@Component
	@PropertySource("classpath:/bre.properties")
	public static class AuthenticationConfig {

		/**
		 * BRE Authentication Mode
		 */
		@Value("${bre.authentication.mode}")
		protected String mode;

		/**
		 * BRE Authentication Basic Token
		 */
		@Value("${bre.authentication.basic.token}")
		protected String basicToken;

		/**
		 * BRE Authentication User Name
		 */
		@Value("${bre.authentication.username}")
		protected String username;

		/**
		 * BRE Authentication Password
		 */
		@Value("${bre.authentication.password}")
		protected String password;

		@Component
		@PropertySource("classpath:/bre.properties")
		public static class UriConfig {

			@Autowired
			protected RepositoryUri repository;

			@Autowired
			protected InvokerUri invoker;

			@Component
			@PropertySource("classpath:/bre.properties")
			public static class RepositoryUri {

				/**
				 * BRE Repository URL
				 */
				@Value("${bre.uri.repository.root}")
				protected String root;

				/**
				 * BRE Repository URL
				 */
				@Value("${bre.uri.repository.xsrf}")
				protected String xsrf;

				public String getRoot() {
					return root;
				}

				public void setRoot(String root) {
					this.root = root;
				}

				public String getXsrf() {
					return xsrf;
				}

				public void setXsrf(String xsrf) {
					this.xsrf = xsrf;
				}

			}

			@Component
			@PropertySource("classpath:/bre.properties")
			public static class InvokerUri {

				/**
				 * BRE Invoker URL
				 */
				@Value("${bre.uri.invoker.root}")
				protected String root;

				/**
				 * BRE Repository URL
				 */
				@Value("${bre.uri.invoker.xsrf}")
				protected String xsrf;

				public String getRoot() {
					return root;
				}

				public void setRoot(String root) {
					this.root = root;
				}

				public String getXsrf() {
					return xsrf;
				}

				public void setXsrf(String xsrf) {
					this.xsrf = xsrf;
				}
			}

			public RepositoryUri getRepository() {
				return repository;
			}

			public void setRepository(RepositoryUri repository) {
				this.repository = repository;
			}

			public InvokerUri getInvoker() {
				return invoker;
			}

			public void setInvoker(InvokerUri invoker) {
				this.invoker = invoker;
			}
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getBasicToken() {
			return basicToken;
		}

		public void setBasicToken(String basicToken) {
			this.basicToken = basicToken;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	public AuthenticationConfig getAuthentication() {
		return authentication;
	}

	public void setAuthentication(AuthenticationConfig authentication) {
		this.authentication = authentication;
	}

	public UriConfig getUri() {
		return uri;
	}

	public void setUri(UriConfig uri) {
		this.uri = uri;
	}

}
