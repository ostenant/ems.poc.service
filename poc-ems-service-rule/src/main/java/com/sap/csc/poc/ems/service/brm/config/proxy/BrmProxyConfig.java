package com.sap.csc.poc.ems.service.brm.config.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Chen
 *
 */
@Component
public class BrmProxyConfig {

	@Autowired
	protected HttpProxyConfig httpProxyConfig;

	@Autowired
	protected HttpsProxyConfig httpsProxyConfig;

	@Component
	public static class HttpProxyConfig {

		/**
		 * BRE Http Proxy Host
		 */
		@Value("${local.proxy.http.host:}")
		protected String host;

		/**
		 * BRE Http Proxy Port
		 */
		@Value("${local.proxy.http.port:}")
		protected Integer port;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

	}

	@Component
	public static class HttpsProxyConfig {

		/**
		 * BRE Https Proxy Host
		 */
		@Value("${local.proxy.https.host:}")
		protected String host;

		/**
		 * BRE Https Proxy Port
		 */
		@Value("${local.proxy.https.port:}")
		protected Integer port;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

	}

	public HttpProxyConfig getHttpProxyConfig() {
		return httpProxyConfig;
	}

	public void setHttpProxyConfig(HttpProxyConfig httpProxyConfig) {
		this.httpProxyConfig = httpProxyConfig;
	}

	public HttpsProxyConfig getHttpsProxyConfig() {
		return httpsProxyConfig;
	}

	public void setHttpsProxyConfig(HttpsProxyConfig httpsProxyConfig) {
		this.httpsProxyConfig = httpsProxyConfig;
	}

}
