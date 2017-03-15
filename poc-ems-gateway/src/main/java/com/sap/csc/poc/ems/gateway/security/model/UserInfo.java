package com.sap.csc.poc.ems.gateway.security.model;

import com.sap.csc.poc.ems.model.enumeration.UserStatus;
import com.sap.csc.poc.ems.model.jpa.permission.User;

public class UserInfo extends ModificatoryEntityInfo {

	private static final long serialVersionUID = -4288429348716518318L;

	private long id;

	private String firstName;
	private String lastName;
	private String loginId;
	private String loginName;

	private UserStatus status;

	public UserInfo() {
		super();
	}

	public UserInfo(User user) {
		super(user);
		if (user == null) {
			return;
		}
		this.setId(user.getId());
		this.setFirstName(user.getProfile().getFirstName());
		this.setLastName(user.getProfile().getLastName());
		this.setLoginId(user.getCertification().getLoginId());
		this.setLoginName(user.getCertification().getLoginName());
		this.setStatus(user.getStatus());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

}
