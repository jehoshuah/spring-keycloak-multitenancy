package com.ct.repository;

import org.springframework.data.repository.CrudRepository;

import com.ct.jpa.REALMS;

public interface RealmsRepository extends CrudRepository<REALMS, String> {
	Iterable<REALMS> findAll();
	REALMS findByRealm(String realm);
}