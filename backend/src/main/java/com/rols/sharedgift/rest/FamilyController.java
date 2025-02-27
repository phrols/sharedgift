package com.rols.sharedgift.rest;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rols.sharedgift.model.Family;
import com.rols.sharedgift.repository.FamilyRepository;

@RestController
@RequestMapping("/api")
public class FamilyController {

	private final FamilyRepository repository;

	FamilyController(FamilyRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/family")
	List<Family> all() {
		return repository.findAll();
	}

	// Single item
	@GetMapping("/family/{id}")
	Family one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Family"));
	}


	@DeleteMapping("/family/{id}")
	void deleteFamily(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

