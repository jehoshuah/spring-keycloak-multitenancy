package com.ct.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ct.jpa.USER_REALMS;
import com.ct.repository.UserRealmsRepository;

@RestController
public class RealmController {

	@Autowired
	UserRealmsRepository repo;

	@CrossOrigin
	@RequestMapping(value = "/getAllRealms", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserRealms() throws Exception {
		return ResponseEntity.ok(repo.findAll());
	}

	@CrossOrigin
	@RequestMapping(value = "/getUserRealms", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserRealms(@RequestParam("user") String user) throws Exception {
		return ResponseEntity.ok(repo.findByUser(user));
	}

	@CrossOrigin
	@RequestMapping(value = "/postUserRealm", method = RequestMethod.POST)
	public ResponseEntity<?> postUserRealm(@RequestParam("user") String user, @RequestBody String realm)
			throws Exception {
		USER_REALMS userRealm = repo.findByUser(user);
		ArrayList<String> realms = new ArrayList<String>();
		String realmsStringArray[];

		if (userRealm == null) {
			realmsStringArray = new String[1];
			realmsStringArray[0] = realm;

		} else {
			realmsStringArray = userRealm.getRealms();
			for (String text : realmsStringArray) {
				realms.add(text);
			}
			realms.add(realm);
			realmsStringArray = new String[realms.size()];
			for (int j = 0; j < realms.size(); j++) {
				realmsStringArray[j] = realms.get(j);
			}
		}
		USER_REALMS newUserRealm = new USER_REALMS(user, realmsStringArray);
		return ResponseEntity.ok(repo.save(newUserRealm));
	}
}