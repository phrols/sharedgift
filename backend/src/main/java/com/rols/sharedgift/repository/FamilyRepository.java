package com.rols.sharedgift.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rols.sharedgift.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {
	
	public Family findByName(String name);

}
