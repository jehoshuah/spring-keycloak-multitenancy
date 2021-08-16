package com.ct.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_REALMS", schema = "CONTROLTOWER_DEMO")

public class USER_REALMS implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;

	@Id
	@Column(name = "USER")
	private String user;
	@Column(name = "REALMS")
	private String[] realms;

	public USER_REALMS() {
	}

	public USER_REALMS(String user, String[] realms) {
		this.setUser(user);
		this.setRealms(realms);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String[] getRealms() {
		return realms;
	}

	public void setRealms(String[] realms) {
		this.realms = realms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}