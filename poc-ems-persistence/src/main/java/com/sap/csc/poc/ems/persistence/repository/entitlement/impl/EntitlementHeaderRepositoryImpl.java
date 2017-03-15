package com.sap.csc.poc.ems.persistence.repository.entitlement.impl;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHeader;
import com.sap.csc.poc.ems.model.jpa.entitlement.EntitlementHeader_;
import com.sap.csc.poc.ems.persistence.repository.entitlement.EntitlementHeaderRepository;

@Repository
public class EntitlementHeaderRepositoryImpl extends SimpleJpaRepository<EntitlementHeader, Long> implements EntitlementHeaderRepository {

	private static final String[] SEARCHING_FIELDS = new String[] { EntitlementHeader_.title.getName(), EntitlementHeader_.description.getName(),
		EntitlementHeader_.customerID.getName(), EntitlementHeader_.customerName.getName(), EntitlementHeader_.documentID.getName() };

	@Autowired
	public EntitlementHeaderRepositoryImpl(EntityManager em) {
		super(EntitlementHeader.class, em);
	}

	@Override
	public Page<EntitlementHeader> search(String keyword, PageRequest pageRequest) {
		if (StringUtils.isBlank(keyword)) {
			return super.findAll(pageRequest);
		}
		else {
			return super.findAll((root, query, cb) -> {
				String keywordClause = "%" + keyword + "%";
				return cb.or(Arrays.asList(SEARCHING_FIELDS).stream().map(field -> cb.like(root.get(field), keywordClause)).collect(Collectors
					.toList()).toArray(new Predicate[SEARCHING_FIELDS.length]));
			}, pageRequest);
		}
	}
}
