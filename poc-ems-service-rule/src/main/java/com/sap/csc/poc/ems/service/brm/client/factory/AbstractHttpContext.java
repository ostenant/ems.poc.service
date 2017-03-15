package com.sap.csc.poc.ems.service.brm.client.factory;

import javax.net.ssl.SSLContext;

import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.DnsResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractHttpContext {

	@Autowired
	@Qualifier("cookieStore")
	private CookieStore cookieStore;

	@Autowired
	@Qualifier("credentialsProvider")
	private CredentialsProvider credentialsProvider;

	@Autowired
	@Qualifier("dnsResolver")
	private DnsResolver dnsResolver;

	@Autowired
	@Qualifier("sslContext")
	private SSLContext sslContext;

	@Autowired
	@Qualifier("requestConfig")
	private RequestConfig requestConfig;

	protected static final String HTTPS = "https";

	protected static final String HTTP = "http";

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public CredentialsProvider getCredentialsProvider() {
		return credentialsProvider;
	}

	public DnsResolver getDnsResolver() {
		return dnsResolver;
	}

	public SSLContext getSslContext() {
		return sslContext;
	}

	public RequestConfig getRequestConfig() {
		return requestConfig;
	}

}
