package com.ct.model;

import java.io.Serializable;

public class UserRealms implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String user;
	private String realms;

	public UserRealms() {
	}

	public UserRealms(String user, String realms) {
		this.user = user;
		this.realms = realms;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRealms() {
		return realms;
	}

	public void setRealms(String realms) {
		this.realms = realms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}