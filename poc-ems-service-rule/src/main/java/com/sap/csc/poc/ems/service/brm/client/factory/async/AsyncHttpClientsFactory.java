package com.sap.csc.poc.ems.service.brm.client.factory.async;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.codecs.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.nio.codecs.DefaultHttpResponseParser;
import org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionFactory;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.nio.NHttpMessageParserFactory;
import org.apache.http.nio.NHttpMessageWriterFactory;
import org.apache.http.nio.conn.ManagedNHttpClientConnection;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.nio.conn.NHttpConnectionFactory;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.nio.util.HeapByteBufferAllocator;
import org.apache.http.util.CharArrayBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;

import com.sap.csc.poc.ems.service.brm.client.factory.AbstractHttpContext;
import com.sap.csc.poc.ems.service.brm.config.http.BasicHttpContextConfig;

public class AsyncHttpClientsFactory extends AbstractHttpContext implements AbstractAsyncHttpClientsFactory {

	private NHttpClientConnectionManager nHttpClientConnectionManager;

	public void setnHttpClientConnectionManager(NHttpClientConnectionManager nHttpClientConnectionManager) {
		this.nHttpClientConnectionManager = nHttpClientConnectionManager;
	}

	@Bean
	public ConnectingIOReactor connectingIOReactor() {
		IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
				// Set thread count according to available processors
				.setIoThreadCount(Runtime.getRuntime().availableProcessors())
				// Set connection timeout
				.setConnectTimeout(30000)
				//
				.setSoTimeout(30000).build();

		// Create a custom I/O reactor
		ConnectingIOReactor ioReactor = null;
		try {
			ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
			return ioReactor;
		} catch (IOReactorException e) {
			e.printStackTrace();
			return ioReactor;
		}
	}

	@Bean
	public NHttpConnectionFactory<ManagedNHttpClientConnection> nioHttpConnectionFactory() {
		NHttpMessageWriterFactory<HttpRequest> requestWriterFactory = new DefaultHttpRequestWriterFactory();
		// Use custom message parser / writer to customize the way HTTP
		NHttpMessageParserFactory<HttpResponse> responseParserFactory = (buffer, constraints) -> {
			LineParser lineParser = new BasicLineParser() {

				@Override
				public Header parseHeader(final CharArrayBuffer buffer) {
					try {
						return super.parseHeader(buffer);
					} catch (ParseException ex) {
						// If errors
						return new BasicHeader("Error", ex.getLocalizedMessage());
					}
				}

			};
			return new DefaultHttpResponseParser(buffer, lineParser, DefaultHttpResponseFactory.INSTANCE, constraints);
		};

		NHttpConnectionFactory<ManagedNHttpClientConnection> connFactory = new ManagedNHttpClientConnectionFactory(
				requestWriterFactory, responseParserFactory, HeapByteBufferAllocator.INSTANCE);

		return connFactory;
	}

	@Bean
	public ConnectionConfig connectionConfig() {
		// Create message constraints
		MessageConstraints messageConstraints = MessageConstraints.custom()
				// Set max header count
				.setMaxHeaderCount(200)
				// Set max line counts of content
				.setMaxLineLength(30000).build();

		// Create connection configuration
		ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
				.setMessageConstraints(messageConstraints).build();

		return connectionConfig;
	}

	@Bean
	public Registry<SchemeIOSessionStrategy> sessionStrategyRegistry() {
		// Set socket factory agent for https and http
		Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
				// register http
				.register(HTTP, NoopIOSessionStrategy.INSTANCE)
				// register https with given sslContext
				.register(HTTPS, new SSLIOSessionStrategy(getSslContext())).build();

		return sessionStrategyRegistry;
	}

	@Bean(name = "nHttpClientConnectionManager", destroyMethod = "shutdownConnection")
	public PoolingNHttpClientConnectionManager nHttpClientConnectionManager(ConnectingIOReactor ioReactor,
			NHttpConnectionFactory<ManagedNHttpClientConnection> connFactory,
			Registry<SchemeIOSessionStrategy> sessionStrategyRegistry) {
		// Create a connection manager with custom configuration.
		PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioReactor,
				connFactory, sessionStrategyRegistry, getDnsResolver());

		// Set max http connections
		connectionManager.setMaxTotal(100);
		// Set max route for per connection
		connectionManager.setDefaultMaxPerRoute(10);
		// Close idle connections per 5 mins
		connectionManager.closeIdleConnections(10, TimeUnit.MINUTES);
		// Save into local variable in case shuttdown
		setnHttpClientConnectionManager(connectionManager);

		return connectionManager;
	}

	@Bean
	public HttpAsyncClientBuilder httpAsyncClientBuilder(NHttpClientConnectionManager connectionManager) {

		HttpAsyncClientBuilder builder = HttpAsyncClients.custom()
				// Set http connection pool manager
				.setConnectionManager(connectionManager)
				// Set request config for global
				.setDefaultRequestConfig(getRequestConfig())
				// Set credentials Provider
				.setDefaultCredentialsProvider(getCredentialsProvider())
				// Set cookie store
				.setDefaultCookieStore(getCookieStore());

		final Collection<Header> defaultHeaders = BasicHttpContextConfig.defaultHeaders();
		if (CollectionUtils.isNotEmpty(defaultHeaders)) {
			// Set default Headers
			builder.setDefaultHeaders(defaultHeaders);
		}

		final Map<String, HttpHost> proxyHosts = BasicHttpContextConfig.proxyHosts();
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

	@Bean
	public HttpComponentsAsyncClientHttpRequestFactory asyncClientHttpRequestFactory(
			HttpAsyncClientBuilder httpAsyncClientBuilder) {
		HttpComponentsAsyncClientHttpRequestFactory asyncClientHttpRequestFactory = new HttpComponentsAsyncClientHttpRequestFactory(
				httpAsyncClientBuilder.build());

		return asyncClientHttpRequestFactory;
	}

	public void shutdownConnection() {
		if (nHttpClientConnectionManager != null) {
			nHttpClientConnectionManager.closeExpiredConnections();
			try {
				nHttpClientConnectionManager.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				nHttpClientConnectionManager = null;
			}
		}
	}

}
