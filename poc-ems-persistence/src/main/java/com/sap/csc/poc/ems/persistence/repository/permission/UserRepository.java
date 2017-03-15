package com.sap.csc.poc.ems.persistence.repository.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sap.csc.poc.ems.model.jpa.permission.User;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByLoginId(String username);

}