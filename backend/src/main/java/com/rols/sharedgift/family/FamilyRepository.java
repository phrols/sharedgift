package com.rols.sharedgift.family;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
	
	public Family findByName(String name);

}
