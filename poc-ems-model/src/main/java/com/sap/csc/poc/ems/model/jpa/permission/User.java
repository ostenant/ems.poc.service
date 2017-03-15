package com.sap.csc.poc.ems.model.jpa.permission;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.sap.csc.poc.ems.model.enumeration.UserStatus;
import com.sap.csc.poc.ems.model.jpa.ModificatoryEntity;

/**
 * @author Diouf Du
 */
@Entity
public class User extends ModificatoryEntity implements Serializable {

	private static final long serialVersionUID = -3780267302524852783L;

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.ACTIVE;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private UserCertification certification;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private UserProfile profile;

	@ManyToMany(mappedBy = "users")
	protected Set<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserCertification getCertification() {
		return certification;
	}

	public void setCertification(UserCertification certification) {
		this.certification = certification;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
