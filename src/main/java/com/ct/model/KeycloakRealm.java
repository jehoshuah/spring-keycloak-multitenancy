package com.ct.model;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class KeycloakRealm {

	@Value("${keycloak.auth-server-url}")
	private static String authServerURL;
	@Value("${keycloak.ssl-required}")
	private static String sslRequired;
	@Value("${keycloak.resource}")
	private static String resource;
	@Value("${keycloak.public-client}")
	private static boolean publicClient;
	@Value("${keycloak.confidential-port}")
	private static int confidentialPort;
	
	public static JSONObject createKeycloakJSON(String realm) throws IOException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("realm", realm);
		jsonObject.put("auth-server-url", authServerURL);
		jsonObject.put("ssl-required", sslRequired);
		jsonObject.put("resource", resource);
		jsonObject.put("public-client", publicClient);
		jsonObject.put("confidential-port", confidentialPort);
		
		return jsonObject;
	}
}
