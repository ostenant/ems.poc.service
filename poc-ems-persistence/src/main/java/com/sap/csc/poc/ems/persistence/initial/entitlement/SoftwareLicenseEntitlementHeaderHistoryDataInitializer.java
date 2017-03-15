package com.sap.csc.poc.ems.persistence.initial.entitlement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.enumeration.EntitlementOperation;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeader;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeaderHistory;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.entitlement.SoftwareLicenseEntitlementHeaderHistoryRepository;

@Component
public class SoftwareLicenseEntitlementHeaderHistoryDataInitializer extends JpaDataInitializer<SoftwareLicenseEntitlementHeaderHistory> {

	@Autowired
	private SoftwareLicenseEntitlementHeaderHistoryRepository entitlementItemRepository;

	@Autowired
	protected SoftwareLicenseEntitlementHeaderDataInitializer softwareLicenseEntitlementHeaderDataInitializer;

	@Override
	public JpaRepository<SoftwareLicenseEntitlementHeaderHistory, Long> getRepository() {
		return entitlementItemRepository;
	}

	@Override
	public Collection<SoftwareLicenseEntitlementHeaderHistory> create() {
		ArrayList<SoftwareLicenseEntitlementHeaderHistory> histories = new ArrayList<>();
		for (SoftwareLicenseEntitlementHeader header : softwareLicenseEntitlementHeaderDataInitializer.get()) {

			// Header History
			int historyCount = RandomUtils.nextInt(0, 20);
			header.setItems(new ArrayList<>(historyCount));
			for (int j = 1; j <= historyCount; j++) {
				SoftwareLicenseEntitlementHeaderHistory headerHistory = new SoftwareLicenseEntitlementHeaderHistory();
				headerHistory.setOperation(EntitlementOperation.values()[RandomUtils.nextInt(0, EntitlementOperation.values().length)]);
				headerHistory.setAmount(new BigDecimal(RandomUtils.nextInt(5, 15) - 10));
				headerHistory.setSource(header);
				histories.add(headerHistory);
			}
		}
		return histories;
	}

}
