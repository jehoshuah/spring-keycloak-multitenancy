package com.ct.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REALMS", schema = "CONTROLTOWER_DEMO")

public class REALMS implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;

	@Id
	@Column(name = "REALM")
	private String realm;
	@Column(name = "KEYCLOAKJSON")
	private String keycloakJSON;

	public REALMS() {
	}

	public REALMS(String realm, String keycloakJSON) {
		this.setRealm(realm);
		this.setKeycloakJSON(keycloakJSON);
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getKeycloakJSON() {
		return keycloakJSON;
	}

	public void setKeycloakJSON(String keycloakJSON) {
		this.keycloakJSON = keycloakJSON;
	}

	
	@Override
	public String toString() {
		return "REALMS [realm=" + realm + ", keycloakJSON=" + keycloakJSON + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}