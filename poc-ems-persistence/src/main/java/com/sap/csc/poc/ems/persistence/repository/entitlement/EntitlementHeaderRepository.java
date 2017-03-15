package com.sap.csc.poc.ems.persistence.repository.entitlement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHeader;

@NoRepositoryBean
public interface EntitlementHeaderRepository extends JpaRepository<EntitlementHeader, Long> {

	Page<EntitlementHeader> search(String keyword, PageRequest pageRequest);

}