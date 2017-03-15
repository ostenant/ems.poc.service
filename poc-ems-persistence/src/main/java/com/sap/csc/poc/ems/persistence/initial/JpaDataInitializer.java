package com.sap.csc.poc.ems.persistence.initial;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

@Cacheable
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public abstract class JpaDataInitializer<TEntity extends Serializable> {

	@Autowired
	protected EntityManager entityManager;

	protected abstract JpaRepository<TEntity, Long> getRepository();

	@Transactional
	protected abstract Iterable<TEntity> create();

	protected Long count() {
		return this.getRepository().count();
	}

	protected List<TEntity> load() {
		return this.getRepository().findAll();
	}

	@PostConstruct
	@Transactional
	public List<TEntity> get() {
		List<TEntity> loadedEntities = load();
		if (CollectionUtils.isEmpty(loadedEntities) && this.getRepository() != null) {
			List<TEntity> entities = this.getRepository().save(create());
			return entities;
		}
		else {
			return load();
		}
	}
}
