package com.sap.csc.poc.ems.model.jpa.permission;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Privilege implements Serializable {

	private static final long serialVersionUID = -6476576158733707385L;

	@Id
	@GeneratedValue
	private Long id;

	protected String name;
	protected String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Privilege parent;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private Set<Privilege> children;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Privilege getParent() {
		return this.parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return this.children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}
}