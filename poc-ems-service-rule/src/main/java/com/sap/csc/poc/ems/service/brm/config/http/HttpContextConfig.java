package com.sap.csc.poc.ems.service.brm.config.http;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.DnsResolver;

import com.google.common.collect.Maps;

public interface HttpContextConfig {

	public static Collection<Header> defaultHeaders() {
		return Collections.emptyList();
	}

	public static Map<String, HttpHost> proxyHosts() {
		return Maps.newHashMap();
	}

	public CookieStore cookieStore();

	public CredentialsProvider credentialsProvider();

	public DnsResolver dnsResolver();

	public SSLContext sslContext();

	public RequestConfig requestConfig();

}
