package com.sap.csc.poc.ems.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.gateway.security.model.AuthenticatedUser;
import com.sap.csc.poc.ems.model.jpa.permission.User;
import com.sap.csc.poc.ems.persistence.repository.permission.UserRepository;

@Component
public class SpringJpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private AuthenticatedUser authenticatedUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOneByLoginId(username);
		if (user == null) {
			return null;
		}
		return this.authenticatedUser = new AuthenticatedUser(user);
	}

	public void refresh() {
		if (authenticatedUser != null && authenticatedUser.getUser() != null) {
			authenticatedUser.refresh(userRepository.findOne(authenticatedUser.getUser().getId()));
		}
	}

	public AuthenticatedUser getAuthenticatedUser() {
		return authenticatedUser;
	}
}
