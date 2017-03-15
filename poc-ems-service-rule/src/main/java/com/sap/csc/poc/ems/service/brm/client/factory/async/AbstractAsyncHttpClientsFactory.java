package com.sap.csc.poc.ems.service.brm.client.factory.async;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.nio.conn.ManagedNHttpClientConnection;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.nio.conn.NHttpConnectionFactory;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;

public interface AbstractAsyncHttpClientsFactory {

	public ConnectingIOReactor connectingIOReactor();

	public NHttpConnectionFactory<ManagedNHttpClientConnection> nioHttpConnectionFactory();

	public ConnectionConfig connectionConfig();

	/**
	 * Create a registry of custom connection session strategies
	 * 
	 * @return
	 */
	public Registry<SchemeIOSessionStrategy> sessionStrategyRegistry();

	public PoolingNHttpClientConnectionManager nHttpClientConnectionManager(
			@Qualifier("connectingIOReactor") ConnectingIOReactor ioReactor,
			@Qualifier("nioHttpConnectionFactory") NHttpConnectionFactory<ManagedNHttpClientConnection> connFactory,
			@Qualifier("sessionStrategyRegistry") Registry<SchemeIOSessionStrategy> sessionStrategyRegistry);

	public HttpAsyncClientBuilder httpAsyncClientBuilder(
			@Qualifier("nHttpClientConnectionManager") NHttpClientConnectionManager connectionManager);

	public HttpComponentsAsyncClientHttpRequestFactory asyncClientHttpRequestFactory(
			@Qualifier("httpAsyncClientBuilder") HttpAsyncClientBuilder httpAsyncClientBuilder);

	public void shutdownConnection();

}
