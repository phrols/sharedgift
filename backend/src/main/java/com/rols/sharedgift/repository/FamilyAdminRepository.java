package com.rols.sharedgift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rols.sharedgift.model.FamilyAdmin;

public interface FamilyAdminRepository extends JpaRepository<FamilyAdmin, Long> {
	
	public List<FamilyAdmin> findByEmail(String email);

}