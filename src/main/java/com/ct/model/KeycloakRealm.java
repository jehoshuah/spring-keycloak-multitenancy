package com.ct.model;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class KeycloakRealm {

	private String realm;
	@Value("${app.keycloak.auth-server-url}")
	private String authServerURL;
	@Value("${app.keycloak.ssl-required}")
	private String sslRequired;
	@Value("${app.keycloak.resource}")
	private String resource;
	@Value("${app.keycloak.public-client}")
	private boolean publicClient;
	@Value("${app.keycloak.confidential-port}")
	private int confidentialPort;
	
	public KeycloakRealm() {}
	
	public KeycloakRealm(String realm) {
		this.setRealm(realm);
	}
	
	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getAuthServerURL() {
		return authServerURL;
	}

	public void setAuthServerURL(String authServerURL) {
		this.authServerURL = authServerURL;
	}

	public String getSslRequired() {
		return sslRequired;
	}

	public void setSslRequired(String sslRequired) {
		this.sslRequired = sslRequired;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public boolean isPublicClient() {
		return publicClient;
	}

	public void setPublicClient(boolean publicClient) {
		this.publicClient = publicClient;
	}

	public int getConfidentialPort() {
		return confidentialPort;
	}

	public void setConfidentialPort(int confidentialPort) {
		this.confidentialPort = confidentialPort;
	}

	public static JSONObject createKeycloakJSON(String realm) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("realm", realm);
		
		//TODO: for DEV
		jsonObject.put("auth-server-url", "https://keycloak.it-control-tower-160619-f72ef11f3ab089a8c677044eb28292cd-0000.sjc03.containers.appdomain.cloud/auth/");
		//TODO: for Local
//		jsonObject.put("auth-server-url", "http://localhost:8080/auth/");

		jsonObject.put("ssl-required", "external");
		jsonObject.put("resource", "web");
		jsonObject.put("public-client", true);
		jsonObject.put("confidential-port", 0);
		
		return jsonObject;
	}
}
