package com.sap.csc.poc.ems.persistence.repository.entitlement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.csc.poc.ems.model.jpa.entitlement.softwarelicense.SoftwareLicenseEntitlementHeaderHistory;

@Repository
public interface SoftwareLicenseEntitlementHeaderHistoryRepository extends JpaRepository<SoftwareLicenseEntitlementHeaderHistory, Long> {

}