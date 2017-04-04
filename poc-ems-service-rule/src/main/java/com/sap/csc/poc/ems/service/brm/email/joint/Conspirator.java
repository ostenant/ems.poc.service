package com.sap.csc.poc.ems.service.brm.email.joint;

import java.io.Serializable;

public class Conspirator extends Originator implements Serializable {

	private static final long serialVersionUID = 2085656218949156373L;

	private Integer priority = DEFAULT_PRIORITY;

	private final static Integer DEFAULT_PRIORITY = 5;

	public Conspirator() {
	}

	public Conspirator(String email, String name) {
		this.email = email;
		this.name = name;
		this.priority = DEFAULT_PRIORITY;
	}

	public Conspirator(final String email, final String name, Integer priority) {
		this.email = email;
		this.name = name;
		this.priority = priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

}
