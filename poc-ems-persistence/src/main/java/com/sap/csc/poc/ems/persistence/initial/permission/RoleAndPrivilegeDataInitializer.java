package com.sap.csc.poc.ems.persistence.initial.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.jpa.permission.Privilege;
import com.sap.csc.poc.ems.model.jpa.permission.Role;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.permission.RoleRepository;

@Component
public class RoleAndPrivilegeDataInitializer extends JpaDataInitializer<Role> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public JpaRepository<Role, Long> getRepository() {
		return roleRepository;
	}

	@Override
	public Collection<Role> create() {
		ArrayList<Role> roles = new ArrayList<>();

		// Entitlement Privileges

		Privilege privilegeEntViewList = new Privilege();
		privilegeEntViewList.setName("ENTITLEMENT_VIEW_LIST");
		privilegeEntViewList.setDescription("View all entitlement list");

		Privilege privilegeEntViewDetail = new Privilege();
		privilegeEntViewDetail.setName("ENTITLEMENT_VIEW_DETAIL");
		privilegeEntViewDetail.setDescription("View sepcified entitlement detail");

		Privilege privilegeEntSplit = new Privilege();
		privilegeEntSplit.setName("ENTITLEMENT_SPLIT");
		privilegeEntSplit.setDescription("Split sepcified entitlement profile into multi");

		Privilege privilegeEntMerge = new Privilege();
		privilegeEntMerge.setName("ENTITLEMENT_MERGE");
		privilegeEntMerge.setDescription("Merge sepcified entitlement profiles into one");

		Privilege privilegeEntUpdate = new Privilege();
		privilegeEntUpdate.setName("ENTITLEMENT_UPDATE");
		privilegeEntUpdate.setDescription("Update sepcified entitlement profile");

		// System Admin

		Role systemAdminRole = new Role();
		systemAdminRole.setName("SYSTEM_ADMIN");
		systemAdminRole.setDescription("Administrator of the whole system");
		systemAdminRole.setPrivileges(new HashSet<>(8));
		systemAdminRole.getPrivileges().add(privilegeEntViewList);
		systemAdminRole.getPrivileges().add(privilegeEntViewDetail);
		systemAdminRole.getPrivileges().add(privilegeEntSplit);
		systemAdminRole.getPrivileges().add(privilegeEntMerge);
		systemAdminRole.getPrivileges().add(privilegeEntUpdate);
		roles.add(systemAdminRole);

		// Support User

		Role supportUserRole = new Role();
		supportUserRole.setName("SUPPORT_USER");
		supportUserRole.setDescription("Support user of the whole system");
		supportUserRole.setPrivileges(new HashSet<>(8));
		supportUserRole.getPrivileges().add(privilegeEntViewList);
		supportUserRole.getPrivileges().add(privilegeEntViewDetail);
		roles.add(supportUserRole);

		return roles;
	}

	public Role getByName(String name) {
		return get().stream().filter(role -> StringUtils.equals(role.getName(), name)).findFirst().get();
	}
}
