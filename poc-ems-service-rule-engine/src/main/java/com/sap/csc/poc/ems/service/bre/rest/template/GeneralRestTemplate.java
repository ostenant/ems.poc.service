package com.sap.csc.poc.ems.service.bre.rest.template;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sap.csc.poc.ems.service.bre.ProxyConfig;
import com.sap.csc.poc.ems.service.bre.ProxyConfig.HttpProxyConfig;
import com.sap.csc.poc.ems.service.bre.ProxyConfig.HttpsProxyConfig;

@Component("generalRestTemplate")
public class GeneralRestTemplate extends RestTemplate {

	@Autowired
	protected ProxyConfig proxyConfig;

	@PostConstruct
	public void initial() {
		this.setRequestFactory(createClientHttpRequestFactory());
	}

	public HttpComponentsClientHttpRequestFactory createClientHttpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}

	public CloseableHttpClient httpClient() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setDefaultMaxPerRoute(1);
		connectionManager.setMaxTotal(20);
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(connectionManager);
		// Set proxy
		String proxy = System.getenv("HTTP_PROXY");
		if (StringUtils.isNotBlank(proxy)) {
			proxy = proxy.replace("http://", "");
			int portSplitter = proxy.lastIndexOf(":");
			if (NumberUtils.isNumber(proxy.substring(proxy.lastIndexOf(":") + 1))) {
				String proxyHost = proxy.substring(0, portSplitter);
				Integer proxyPort = Integer.valueOf(proxy.substring(portSplitter + 1));
				httpClientBuilder.setProxy(new HttpHost(proxyHost, proxyPort, "http"));
			}
		}

		if (null != proxyConfig) {
			HttpsProxyConfig httpsProxyConfig = proxyConfig.getHttpsProxyConfig();
			if (null != httpsProxyConfig) {
				String host = httpsProxyConfig.getHost();
				Integer port = httpsProxyConfig.getPort();
				if (StringUtils.isNotBlank(host) && null != port) {
					httpClientBuilder.setProxy(new HttpHost(host, port, "https"));
				}
			}
			HttpProxyConfig httpProxyConfig = proxyConfig.getHttpProxyConfig();
			if (null != httpProxyConfig) {
				String host = httpProxyConfig.getHost();
				Integer port = httpProxyConfig.getPort();
				if (StringUtils.isNotBlank(host) && null != port) {
					httpClientBuilder.setProxy(new HttpHost(host, port, "http"));
				}
			}
		}

		// Build
		return httpClientBuilder.build();
	}
}