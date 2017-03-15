package com.sap.csc.poc.ems.persistence.initial.entitlement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.enumeration.EntitlementStatus;
import com.sap.csc.poc.ems.model.jpa.common.Validity;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeader;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementItem;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.entitlement.SoftwareLicenseEntitlementItemRepository;

@Component
public class SoftwareLicenseEntitlementItemDataInitializer extends JpaDataInitializer<SoftwareLicenseEntitlementItem> {

	@Autowired
	private SoftwareLicenseEntitlementItemRepository entitlementItemRepository;

	@Autowired
	protected SoftwareLicenseEntitlementHeaderDataInitializer softwareLicenseEntitlementHeaderDataInitializer;

	@Override
	public JpaRepository<SoftwareLicenseEntitlementItem, Long> getRepository() {
		return entitlementItemRepository;
	}

	@Override
	public Collection<SoftwareLicenseEntitlementItem> create() {
		ArrayList<SoftwareLicenseEntitlementItem> items = new ArrayList<>();
		for (SoftwareLicenseEntitlementHeader header : softwareLicenseEntitlementHeaderDataInitializer.get()) {
			// Items
			int itemCount = RandomUtils.nextInt(1, 30);
			header.setItems(new ArrayList<>(itemCount));
			for (int j = 1; j <= itemCount; j++) {
				SoftwareLicenseEntitlementItem item = new SoftwareLicenseEntitlementItem();
				item.setStatus(EntitlementStatus.ACTIVE);
				item.setLicenseKey(UUID.randomUUID().toString().replaceAll("-", ""));
				item.setValidity(createRandomValidity());
				header.getItems().add(item);
				item.setHeader(header);
				items.add(item);
			}
		}
		return items;
	}

	private Validity createRandomValidity() {
		Validity validity = new Validity();
		Calendar validityFrom = Calendar.getInstance();
		validityFrom.add(Calendar.DATE, -RandomUtils.nextInt(1, 90));
		validity.setValidityFrom(validityFrom);
		Calendar validityTo = Calendar.getInstance();
		validityTo.add(Calendar.DATE, RandomUtils.nextInt(30, 360));
		validity.setValidityTo(validityTo);
		return validity;
	}
}
