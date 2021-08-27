package com.ct.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ct.jpa.REALMS;
import com.ct.repository.RealmsRepository;

@RestController
public class RealmController {

	@Autowired
	RealmsRepository repo;

//	@CrossOrigin
//	@GetMapping("/tenant/{realm}/get-all")
//	public ResponseEntity<?> getAllRealms(@PathVariable("realm") String realm) throws Exception {
//		return ResponseEntity.ok(repo.findAll());
//	}

	@CrossOrigin
	@GetMapping("/tenant/{realm}/get")
	public ResponseEntity<?> getAllUserRealms(@PathVariable("realm") String realm) throws Exception {
		return ResponseEntity.ok(repo.findByRealm(realm));
	}

	@CrossOrigin
	@PostMapping("/tenant/{realm}/add")
	public ResponseEntity<?> postUserRealm(@PathVariable("realm") String realm, @RequestBody String keycloakJSON)
			throws Exception {
		REALMS realmObj = repo.findByRealm(realm);
		if (realmObj != null) {
			return ResponseEntity.ok(repo.save(new REALMS(realm, keycloakJSON)));
		} else {
			return ResponseEntity.badRequest().body("Realm Exists!");
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/tenant/{realm}/createRealm", method = RequestMethod.GET)
	public ResponseEntity<?> createRealm(@PathVariable("realm") String realm, @RequestParam("tenant") String tenant)
			throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = tenant + ".json";
		File file = new File(classLoader.getResource(".").getFile() + "/" + fileName);
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}
		return ResponseEntity.ok("Created!");
	}
}