package com.ct.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.OIDCHttpFacade;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.springframework.beans.factory.annotation.Autowired;

import com.ct.jpa.REALMS;
import com.ct.model.KeycloakRealm;
import com.ct.repository.RealmsRepository;

public class PathBasedConfigResolver implements KeycloakConfigResolver {

	private final ConcurrentHashMap<String, KeycloakDeployment> cache = new ConcurrentHashMap<>();

	@Autowired
	RealmsRepository repo;

	@SuppressWarnings("unused")
	private static AdapterConfig adapterConfig;

	@Override
	public KeycloakDeployment resolve(OIDCHttpFacade.Request request) {

		String path = request.getURI();
		int multitenantIndex = path.indexOf("tenant/");

		if (multitenantIndex == -1) {
			throw new IllegalStateException("Not able to resolve realm from the request path!");
		}

		String realm = path.substring(path.indexOf("tenant/")).split("/")[1];
		if (realm.contains("?")) {
			realm = realm.split("\\?")[0];
		}

		if (!cache.containsKey(realm)) {
			InputStream is = null;
			String jsonInString;
			REALMS realms = repo.findByRealm(realm);
			if (realms == null) {
				System.out.println("===================NEW REALM==================");
				try {
					jsonInString = KeycloakRealm.createKeycloakJSON(realm).toString();
					repo.save(new REALMS(realm, jsonInString));
					is = new ByteArrayInputStream(jsonInString.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("===================EXISTING REALM==================");
				jsonInString = realms.getKeycloakJSON().replace("\\\"", "\"");
				System.out.println(jsonInString);
				is = new ByteArrayInputStream(jsonInString.getBytes());
			}
			cache.put(realm, KeycloakDeploymentBuilder.build(is));
		}

		return cache.get(realm);
	}

	static void setAdapterConfig(AdapterConfig adapterConfig) {
		PathBasedConfigResolver.adapterConfig = adapterConfig;
	}

}