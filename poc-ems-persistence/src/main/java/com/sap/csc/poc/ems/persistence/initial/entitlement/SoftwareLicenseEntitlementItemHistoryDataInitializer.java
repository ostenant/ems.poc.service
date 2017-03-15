package com.sap.csc.poc.ems.persistence.initial.entitlement;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.enumeration.EntitlementOperation;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementItem;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementItemHistory;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.entitlement.SoftwareLicenseEntitlementItemHistoryRepository;

@Component
public class SoftwareLicenseEntitlementItemHistoryDataInitializer extends JpaDataInitializer<SoftwareLicenseEntitlementItemHistory> {

	@Autowired
	private SoftwareLicenseEntitlementItemHistoryRepository entitlementItemHistoryRepository;

	@Autowired
	protected SoftwareLicenseEntitlementItemDataInitializer softwareLicenseEntitlementItemDataInitializer;

	@Override
	public JpaRepository<SoftwareLicenseEntitlementItemHistory, Long> getRepository() {
		return entitlementItemHistoryRepository;
	}

	@Override
	public Collection<SoftwareLicenseEntitlementItemHistory> create() {
		ArrayList<SoftwareLicenseEntitlementItemHistory> histories = new ArrayList<>();
		for (SoftwareLicenseEntitlementItem item : softwareLicenseEntitlementItemDataInitializer.get()) {
			// Item History
			int historyCount = RandomUtils.nextInt(0, 20);
			for (int j = 1; j <= historyCount; j++) {
				SoftwareLicenseEntitlementItemHistory itemHistory = new SoftwareLicenseEntitlementItemHistory();
				itemHistory.setOperation(EntitlementOperation.values()[RandomUtils.nextInt(0, EntitlementOperation.values().length)]);
				itemHistory.setSource(item);
				histories.add(itemHistory);
			}
		}
		return histories;
	}

}
