package com.ct.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ct.repository.RealmsRepository;

@RestController
public class CatalogController {

	@Autowired
	RealmsRepository repo;
	
	@GetMapping("/tenant/{realm}/catalog")
	public void listCatalogBranch1(@PathVariable("realm") String realm, @RequestParam("redirectURL") String redirectURL, HttpServletResponse response) throws IOException {
		response.sendRedirect(redirectURL);
	}
	
	@GetMapping("/tenant/{realm}/get-all")
	public ResponseEntity<?> getAllRealms(@PathVariable("realm") String realm) throws Exception {
		return ResponseEntity.ok(repo.findAll());
	}

	@SuppressWarnings("unchecked")
	private String getUserInfo() {

		KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
				.getAuthentication();

		final Principal principal = (Principal) authentication.getPrincipal();

		String tokenInfo = null;
		if (principal instanceof KeycloakPrincipal) {

			KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
			KeycloakSecurityContext ksc = kPrincipal.getKeycloakSecurityContext();
			IDToken token = ksc.getIdToken();
			AccessToken accessToken = kPrincipal.getKeycloakSecurityContext().getToken();
			tokenInfo = accessToken.getSubject();

			// this value is the one use to call another service as bearer token
			// Authorization : Bearer kcs.getTokenString()
			// use this link to read the token https://jwt.io
			System.out.println(ksc.getTokenString());
			System.out.println(accessToken.getGivenName());
			System.out.println(accessToken.getFamilyName());
		}

		return "userInfo " + tokenInfo;
	}
}
