package com.sap.csc.poc.ems.service.brm.client.factory.sync;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public interface AbstractHttpClientsFactory {

	public RegistryBuilder<ConnectionSocketFactory> registryBuilder();

	public PoolingHttpClientConnectionManager connectionManager(
			@Qualifier("registryBuilder") RegistryBuilder<ConnectionSocketFactory> registryBuilder);

	public HttpClientBuilder httpClientBuilder(
			@Qualifier("httpClientConnectionManager") HttpClientConnectionManager connectionManager);

	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory(
			@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder);

	public void shutdownConnection();

}
