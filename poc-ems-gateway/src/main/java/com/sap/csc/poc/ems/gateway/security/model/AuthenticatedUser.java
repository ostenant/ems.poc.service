package com.sap.csc.poc.ems.gateway.security.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthenticatedUser extends User {

	private static final long serialVersionUID = 8661876527028722128L;

	private UserInfo user;

	public AuthenticatedUser(com.sap.csc.poc.ems.model.jpa.permission.User user) {
		super(
			// Login Id
			user.getCertification().getLoginId(),
			// Password
			user.getCertification().getFakePassword(),
			// Authorities
			CollectionUtils.isEmpty(user.getRoles()) ? Collections.emptyList() : user.getRoles().stream().map(role -> {
				return ListUtils.union(
					// Roles
					Arrays.asList(new SimpleGrantedAuthority[] { new SimpleGrantedAuthority("ROLE_" + role.getName()) }),
					// Privileges
					role.getPrivileges().stream().map(privilege -> new SimpleGrantedAuthority("PRIVILGE_" + privilege.getName())).collect(Collectors
						.toList()));
			})
				// Collect
				.collect(Collectors.toList()).stream().reduce((list1, list2) -> {
					return ListUtils.union(list1, list2);
				}).get().stream().distinct().collect(Collectors.toList()));

		this.user = new UserInfo(user);
	}

	public void refresh(com.sap.csc.poc.ems.model.jpa.permission.User user) {
		this.user = new UserInfo(user);
	}

	public final UserInfo getUser() {
		return user;
	}

	public final void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return super.getPassword();
	}
}
