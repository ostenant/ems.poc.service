package com.sap.csc.poc.ems.service.brm.client.factory.sync;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * @author Vincent Chen
 *
 */
public interface AbstractHttpClientsFactory {

	RegistryBuilder<ConnectionSocketFactory> registryBuilder();

	PoolingHttpClientConnectionManager connectionManager(
			@Qualifier("registryBuilder") RegistryBuilder<ConnectionSocketFactory> registryBuilder);

	HttpClientBuilder httpClientBuilder(
			@Qualifier("httpClientConnectionManager") HttpClientConnectionManager connectionManager);

	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory(
			@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder);

}
