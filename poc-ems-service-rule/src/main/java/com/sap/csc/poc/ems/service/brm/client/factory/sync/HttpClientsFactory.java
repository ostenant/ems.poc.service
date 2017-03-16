package com.sap.csc.poc.ems.service.brm.client.factory.sync;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import com.sap.csc.poc.ems.service.brm.client.factory.AbstractHttpContext;
import com.sap.csc.poc.ems.service.brm.config.http.BasicHttpContextConfig;

@Configuration
public class HttpClientsFactory extends AbstractHttpContext implements AbstractHttpClientsFactory {

	@Autowired
	private BasicHttpContextConfig basicHttpContextConfig;

	@Bean
	@Order(1)
	public RegistryBuilder<ConnectionSocketFactory> registryBuilder() {
		PlainConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(getSslContext());
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create()
				// Register http
				.register(HTTP, plainsf)
				// Register https
				.register(HTTPS, sslsf);

		return registryBuilder;
	}

	@Bean(name = "httpClientConnectionManager", destroyMethod = "shutdown")
	@Order(2)
	public PoolingHttpClientConnectionManager connectionManager(
			RegistryBuilder<ConnectionSocketFactory> registryBuilder) {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
				registryBuilder.build());
		// Set max http connections
		connectionManager.setMaxTotal(100);
		// Set max route for per connection
		connectionManager.setDefaultMaxPerRoute(10);
		// Close idle connections per 5 mins
		connectionManager.closeIdleConnections(10, TimeUnit.MINUTES);
		// Save into local variable in case shuttdown
		return connectionManager;
	}

	@Bean
	@Order(3)
	public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager connectionManager) {

		HttpClientBuilder builder = HttpClients.custom()
				// Set http connection pool manager
				.setConnectionManager(connectionManager)
				// Set request config for global
				.setDefaultRequestConfig(getRequestConfig())
				// Set credentials Provider
				.setDefaultCredentialsProvider(getCredentialsProvider())
				// Set cookie store
				.setDefaultCookieStore(getCookieStore())
				// Set self-defined dns resolver
				.setDnsResolver(getDnsResolver());

		final Collection<Header> defaultHeaders = basicHttpContextConfig.defaultHeaders();
		if (CollectionUtils.isNotEmpty(defaultHeaders)) {
			// Set default Headers
			builder.setDefaultHeaders(defaultHeaders);
		}

		final Map<String, HttpHost> proxyHosts = basicHttpContextConfig.proxyHosts();
		if (CollectionUtils.isNotEmpty(proxyHosts.entrySet())) {
			// Set proxy if exists, prior to http, then https
			if (proxyHosts.containsKey(HTTP)) {
				builder.setProxy(proxyHosts.get(HTTP));
			} else if (proxyHosts.containsKey(HTTPS)) {
				builder.setProxy(proxyHosts.get(HTTPS));
			}
		}
		return builder;

	}

	@Bean("clientHttpRequestFactory")
	@Order(4)
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory(HttpClientBuilder httpClientBuilder) {
		// For RestTemplate
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClientBuilder.build());

		return httpRequestFactory;
	}

}
