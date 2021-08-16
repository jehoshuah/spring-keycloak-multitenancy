package com.ct.repository;

import org.springframework.data.repository.CrudRepository;

import com.ct.jpa.USER_REALMS;

public interface UserRealmsRepository extends CrudRepository<USER_REALMS, String> {
	Iterable<USER_REALMS> findAll();
	USER_REALMS findByUser(String user);
}