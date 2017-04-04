package com.sap.csc.poc.ems.service.brm.email.joint;

import java.io.Serializable;

public class Originator implements Serializable {

	private static final long serialVersionUID = 6247926646267541774L;

	protected String email;
	protected String name;

	public Originator() {
		super();
	}

	public Originator(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Originator [email=" + email + ", name=" + name + "]";
	}

}
