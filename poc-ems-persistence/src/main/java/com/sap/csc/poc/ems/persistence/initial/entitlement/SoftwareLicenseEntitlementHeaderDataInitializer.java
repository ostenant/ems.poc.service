package com.sap.csc.poc.ems.persistence.initial.entitlement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sap.csc.poc.ems.model.enumeration.DocumentType;
import com.sap.csc.poc.ems.model.enumeration.EntitlementStatus;
import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeader;
import com.sap.csc.poc.ems.persistence.initial.JpaDataInitializer;
import com.sap.csc.poc.ems.persistence.initial.permission.UserDataInitializer;
import com.sap.csc.poc.ems.persistence.repository.entitlement.SoftwareLicenseEntitlementHeaderRepository;

@Component
public class SoftwareLicenseEntitlementHeaderDataInitializer extends JpaDataInitializer<SoftwareLicenseEntitlementHeader> {

	@Autowired
	private SoftwareLicenseEntitlementHeaderRepository entitlementRepository;

	@Autowired
	protected UserDataInitializer userDataInitializer;

	@Autowired
	protected EntitlementTypeDataInitializer entitlementTypeDataInitializer;

	@Override
	public JpaRepository<SoftwareLicenseEntitlementHeader, Long> getRepository() {
		return entitlementRepository;
	}

	@Override
	public Collection<SoftwareLicenseEntitlementHeader> create() {
		ArrayList<SoftwareLicenseEntitlementHeader> entitlements = new ArrayList<>();
		for (int i = 1; i <= RandomUtils.nextInt(30, 50); i++) {
			// Header
			SoftwareLicenseEntitlementHeader header = new SoftwareLicenseEntitlementHeader();
			header.setBeginPostingDate(createRandomCalendar(-RandomUtils.nextInt(10, 20)));
			header.setPostingDate(createRandomCalendar(-RandomUtils.nextInt(1, 10)));
			header.setTitle(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(10, 20)));
			header.setDescription(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(20, 50)));
			header.setStatus(RandomUtils.nextInt(1, 10) == 5 ? EntitlementStatus.INACTIVE : EntitlementStatus.ACTIVE);
			header.setDocumentID("SO" + String.valueOf(RandomUtils.nextInt(100000000, 999999999)));
			header.setDocumentType(DocumentType.SALES_ORDER);
			header.setCustomerID("C" + String.valueOf(RandomUtils.nextInt(1000000, 9999999)));
			header.setCustomerName(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(3, 10)));
			if (i % 4 == 0) {
				header.setCustomerName("IBM");
			}
			else if (i % 4 == 1) {
				header.setCustomerName("HP");
			}
			else if (i % 4 == 2) {
				header.setCustomerName("Dell");
			}
			else {
				header.setCustomerName("GE");
			}

			// Header Type
			header.setType(entitlementTypeDataInitializer.getByName("SL"));

			entitlements.add(header);
		}
		return entitlements;
	}

	private Calendar createRandomCalendar(int day) {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, day);
		return date;
	}
}
