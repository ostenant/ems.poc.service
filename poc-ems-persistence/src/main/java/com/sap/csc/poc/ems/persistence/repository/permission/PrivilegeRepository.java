package com.sap.csc.poc.ems.persistence.repository.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.csc.poc.ems.model.jpa.permission.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}