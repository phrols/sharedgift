package com.rols.sharedgift.family;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/family")
	Family newFamily(@RequestBody Family newFamily) {
		return repository.save(newFamily);
	}
	
	@PutMapping("/family/{id}")
	Family replaceFamily(@RequestBody Family newFamily, @PathVariable Long id) {
		return repository.findById(id).map(family -> {
			family.setName(newFamily.getName());
			return repository.save(family);
		}).orElseThrow(() -> new ObjectNotFoundException(id, "Family"));
	}

	@DeleteMapping("/family/{id}")
	void deleteFamily(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

