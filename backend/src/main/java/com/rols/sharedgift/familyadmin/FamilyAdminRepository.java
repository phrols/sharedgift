package com.rols.sharedgift.familyadmin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyAdminRepository extends JpaRepository<FamilyAdmin, Long> {
	
	public Optional<FamilyAdmin> findByEmail(String email);

}