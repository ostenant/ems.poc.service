package com.sap.csc.poc.ems.service.brm.config.http;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MimeTypeUtils;

import com.google.common.collect.Maps;
import com.sap.csc.poc.ems.service.brm.config.property.BrmPropertyHolder;
import com.sap.csc.poc.ems.service.brm.config.proxy.BrmProxyConfig;
import com.sap.csc.poc.ems.service.brm.config.proxy.BrmProxyConfig.HttpProxyConfig;
import com.sap.csc.poc.ems.service.brm.config.proxy.BrmProxyConfig.HttpsProxyConfig;

@Configuration
public final class BasicHttpContextConfig implements HttpContextConfig {

	protected static final List<Header> defaultHeaders = Collections.emptyList();

	protected static final Map<String, HttpHost> proxyHosts = Maps.newHashMap();

	@Autowired
	protected BrmPropertyHolder brmPropertyHolder;

	@Autowired
	protected BrmProxyConfig brmProxyConfig;

	protected final List<String> allowedAuthSchemes = Arrays.asList(AuthSchemes.BASIC, AuthSchemes.DIGEST,
			AuthSchemes.KERBEROS, AuthSchemes.NTLM, AuthSchemes.SPNEGO);

	protected final List<String> preferredAuthSchemes = Arrays.asList(AuthSchemes.BASIC, AuthSchemes.DIGEST);

	protected static final String HTTPS = "https";

	protected static final String HTTP = "http";

	@PostConstruct
	public void initial() {
		// Set body's content-type to json
		defaultHeaders.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE));
		// Accept response as json
		defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT, MimeTypeUtils.APPLICATION_JSON_VALUE));
		// Basic Authorization
		defaultHeaders.add(new BasicHeader(HttpHeaders.AUTHORIZATION, getBasicHeader()));

		// Set Proxy
		if (!proxyHosts.containsKey(HTTP)) {
			HttpProxyConfig httpProxyConfig = brmProxyConfig.getHttpProxyConfig();
			if (httpProxyConfig != null && StringUtils.isNotBlank(httpProxyConfig.getHost())
					&& httpProxyConfig.getPort() > 0) {
				proxyHosts.put(HTTP, new HttpHost(httpProxyConfig.getHost(), httpProxyConfig.getPort(), HTTP));
			}
		} else if (!proxyHosts.containsKey(HTTPS)) {
			HttpsProxyConfig httpsProxyConfig = brmProxyConfig.getHttpsProxyConfig();
			if (httpsProxyConfig != null && StringUtils.isNotBlank(httpsProxyConfig.getHost())
					&& httpsProxyConfig.getPort() > 0) {
				proxyHosts.put(HTTPS, new HttpHost(httpsProxyConfig.getHost(), httpsProxyConfig.getPort(), HTTP));
			}
		}
	}

	public static Collection<Header> defaultHeaders() {
		return defaultHeaders;
	}

	public static Map<String, HttpHost> proxyHosts() {
		return proxyHosts;
	}

	@Bean
	public CookieStore cookieStore() {
		return new BasicCookieStore();
	}

	@Bean
	public CredentialsProvider credentialsProvider() {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		final String basicAuthToken = brmPropertyHolder.getAuthentication().getBasicToken();
		final String username = brmPropertyHolder.getAuthentication().getUsername();
		final String password = brmPropertyHolder.getAuthentication().getPassword();

		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			Credentials credentials = new UsernamePasswordCredentials(username, password);
			credentialsProvider.setCredentials(AuthScope.ANY, credentials);
		} else {
			String usernamePassword = new String(Base64.decodeBase64(basicAuthToken));
			Credentials credentials = new UsernamePasswordCredentials(usernamePassword.split(":")[0],
					usernamePassword.split(":")[1]);
			credentialsProvider.setCredentials(AuthScope.ANY, credentials);
		}

		return credentialsProvider;
	}

	@Bean
	public DnsResolver dnsResolver() {
		return new SystemDefaultDnsResolver() {

			@Override
			public InetAddress[] resolve(final String host) throws UnknownHostException {
				if (host.equalsIgnoreCase("DEV")) {
					return new InetAddress[] { InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }) };
				} else {
					return super.resolve(host);
				}
			}
		};

	}

	@Bean
	public SSLContext sslContext() {
		// Allow https trust all certificate
		SSLContext sslContext = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			sslContext = SSLContexts.custom()
					// if certificate exists
					.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
					// if certificate not exists
					.loadTrustMaterial(null, (chain, authType) -> {
						// Bypass all
						return true;
					}).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		return sslContext;

	}

	@Bean
	public RequestConfig requestConfig() {
		RequestConfig componentRequestConfig = RequestConfig.copy(defaultRequestConfig())
				// Timeout for connection established
				.setConnectTimeout(3000)
				// Timeout for fetching a idle connection from connection pool
				.setConnectionRequestTimeout(3000)
				// Timeout for response
				.setSocketTimeout(5000)
				// Set proxy
				.setProxy(proxyHosts().containsKey(HTTP) ? //
						proxyHosts().get(HTTP) : //
						(proxyHosts().containsKey(HTTPS) ? proxyHosts().get(HTTPS) : defaultRequestConfig().getProxy()))
				.build();

		return componentRequestConfig;
	}

	protected RequestConfig defaultRequestConfig() {

		Builder defaultBuilder = RequestConfig.custom()
				// For non-http cookies
				.setCookieSpec(CookieSpecs.DEFAULT).setExpectContinueEnabled(true)
				// For target host's auth
				.setTargetPreferredAuthSchemes(preferredAuthSchemes)
				// For proxy host's auth
				.setProxyPreferredAuthSchemes(preferredAuthSchemes)
				// Enable auth
				.setAuthenticationEnabled(true)
				// Enable content compression before delivery
				.setContentCompressionEnabled(true)
				// Disallow circular redirect
				.setCircularRedirectsAllowed(false);

		extractFromLocalEnv(defaultBuilder);
		return defaultBuilder.build();
	}

	protected void extractFromLocalEnv(Builder defaultBuilder) {
		// Check system env variables
		String httpProxy = System.getenv("HTTP_PROXY");
		String httpsProxy = System.getenv("HTTPS_PROXY");
		if (StringUtils.isNotBlank(httpProxy)) {
			httpProxy = httpProxy.replace("http://", "");
			int portSplitter = httpProxy.lastIndexOf(":");
			if (NumberUtils.isNumber(httpProxy.substring(httpProxy.lastIndexOf(":") + 1))) {
				String proxyHost = httpProxy.substring(0, portSplitter);
				Integer proxyPort = Integer.valueOf(httpProxy.substring(portSplitter + 1));
				defaultBuilder.setProxy(new HttpHost(proxyHost, proxyPort, "http"));
			}
		} else if (StringUtils.isNotBlank(httpsProxy)) {
			httpsProxy = httpsProxy.replace("https://", "");
			int portSplitter = httpsProxy.lastIndexOf(":");
			if (NumberUtils.isNumber(httpsProxy.substring(httpsProxy.lastIndexOf(":") + 1))) {
				String proxyHost = httpsProxy.substring(0, portSplitter);
				Integer proxyPort = Integer.valueOf(httpsProxy.substring(portSplitter + 1));
				defaultBuilder.setProxy(new HttpHost(proxyHost, proxyPort, "https"));
			}
		}
	}

	protected String getBasicHeader() {
		return "Basic " + brmPropertyHolder.getAuthentication().getBasicToken();
	}

}
