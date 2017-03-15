package com.sap.csc.poc.ems.persistence.initial.entitlement;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementType;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.entitlement.EntitlementTypeRepository;

@Component
public class EntitlementTypeDataInitializer extends JpaDataInitializer<EntitlementType> {

	@Autowired
	private EntitlementTypeRepository entitlementTypeRepository;

	@Override
	public JpaRepository<EntitlementType, Long> getRepository() {
		return entitlementTypeRepository;
	}

	@Override
	public Collection<EntitlementType> create() {
		ArrayList<EntitlementType> entitlementTypes = new ArrayList<>();

		// Software License
		EntitlementType sl = new EntitlementType();
		sl.setName("SL");
		sl.setDescription("Software License");
		entitlementTypes.add(sl);

		return entitlementTypes;
	}

	public EntitlementType getByName(String name) {
		return get().stream().filter(type -> StringUtils.equalsIgnoreCase(type.getName(), name)).findFirst().get();
	}
}
