package com.sap.csc.poc.ems.persistence.initial.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.enumeration.UserStatus;
import com.sap.csc.poc.ems.model.jpa.permission.Role;
import com.sap.csc.poc.ems.model.jpa.permission.User;
import com.sap.csc.poc.ems.model.jpa.permission.UserCertification;
import com.sap.csc.poc.ems.model.jpa.permission.UserProfile;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.permission.UserRepository;

@Component
public class UserDataInitializer extends JpaDataInitializer<User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleAndPrivilegeDataInitializer roleAndPrivilegeDataInitializer;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return userRepository;
	}

	@Override
	public Collection<User> create() {
		ArrayList<User> users = new ArrayList<>();

		User max = new User();
		max.setStatus(UserStatus.ACTIVE);
		max.setProfile(new UserProfile());
		max.getProfile().setFirstName("Max");
		max.getProfile().setLastName("Huang");
		max.setCertification(new UserCertification());
		max.getCertification().setLoginId("P000001");
		max.getCertification().setLoginName("Max Huang");
		max.getCertification().setFakePassword("123456");
		max.setRoles(new HashSet<Role>(1));
		max.getRoles().add(roleAndPrivilegeDataInitializer.getByName("SYSTEM_ADMIN"));
		users.add(max);

		User kevin = new User();
		kevin.setStatus(UserStatus.ACTIVE);
		kevin.setProfile(new UserProfile());
		kevin.getProfile().setFirstName("Kevin");
		kevin.getProfile().setLastName("Li");
		kevin.setCertification(new UserCertification());
		kevin.getCertification().setLoginId("P000002");
		kevin.getCertification().setLoginName("Kevin Li");
		kevin.setRoles(new HashSet<Role>(1));
		kevin.getRoles().add(roleAndPrivilegeDataInitializer.getByName("SUPPORT_USER"));
		users.add(kevin);

		return users;
	}

	public User getByLoginId(String loginId) {
		return get().stream().filter(user -> StringUtils.equals(user.getCertification().getLoginId(), loginId)).findFirst().get();
	}
}
