package com.sap.csc.poc.ems.model.jpa.permission;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserCertification implements Serializable {

	private static final long serialVersionUID = -8779325115445356278L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Login Id for SCI or other IdP to mapping the assert
	 */
	private String loginId;

	/**
	 * Login Name to display on the UI
	 */
	private String loginName;

	/**
	 * Fake password for Spring Security to authenticate
	 */
	private String fakePassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFakePassword() {
		return fakePassword;
	}

	public void setFakePassword(String fakePassword) {
		this.fakePassword = fakePassword;
	}

}
