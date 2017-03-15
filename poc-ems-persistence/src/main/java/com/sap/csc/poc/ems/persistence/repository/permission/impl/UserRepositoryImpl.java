package com.sap.csc.poc.ems.persistence.repository.permission.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.csc.poc.ems.model.jpa.permission.User;
import com.sap.csc.poc.ems.model.jpa.permission.UserCertification_;
import com.sap.csc.poc.ems.model.jpa.permission.User_;
import com.sap.csc.poc.ems.persistence.repository.permission.UserRepository;

@Repository
public class UserRepositoryImpl extends SimpleJpaRepository<User, Long> implements UserRepository {

	@Autowired
	public UserRepositoryImpl(EntityManager em) {
		super(User.class, em);
	}

	@Override
	public User findOneByLoginId(String loginId) {
		return super.findOne((root, query, cb) -> {
			root.fetch("roles", JoinType.LEFT);
			return cb.equal(root.get(User_.certification).get(UserCertification_.loginId), loginId);
		});
	}
}
