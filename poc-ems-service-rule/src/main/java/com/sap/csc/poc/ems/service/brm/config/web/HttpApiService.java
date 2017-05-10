package com.sap.csc.poc.ems.service.brm.config.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HttpApiService {

	private static final int[] HIDDEN_PORTS = new int[] { 80, 443 };

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private HttpServletResponse httpServletResponse;

	protected HttpSession getHttpSession() {
		return this.httpSession;
	}

	protected HttpServletRequest getHttpServletRequest() {
		return this.httpServletRequest;
	}

	protected HttpServletResponse getHttpServletResponse() {
		return this.httpServletResponse;
	}

	public String getServerRootUrl() {
		return String.format("%s://%s%s%s",
			// Schema Name - e.g.: "http" or "https"
			httpServletRequest.getScheme(),
			// Server Name - e.g.: "localhost" or "wechattemplate.com"
			httpServletRequest.getServerName(),
			// Server Port - e.g.: 80
			ArrayUtils.contains(HIDDEN_PORTS, httpServletRequest.getServerPort()) ? "" : (":" + httpServletRequest.getServerPort()),
			// Context Path - e.g.: "wechat/template/xxx" if has
			httpServletRequest.getContextPath());
	}

	public String getClientIp() {
		return httpServletRequest.getRemoteAddr();
	}
}
