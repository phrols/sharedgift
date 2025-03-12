package com.rols.sharedgift.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rols.sharedgift.model.FamilyAdmin;
import com.rols.sharedgift.repository.FamilyAdminRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class FamilyAdminController {

	private final FamilyAdminRepository repository;

	FamilyAdminController(FamilyAdminRepository repository) {
		this.repository = repository;
	}

	// Single item
	@GetMapping("/familyadmin/{id}")
	FamilyAdmin one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FamilyAdmin not found for id %d".formatted(id)));
	}

	@PostMapping("/familyadmin")
	@ResponseStatus(HttpStatus.CREATED)
	FamilyAdmin newFamilyAdmin(@RequestBody FamilyAdmin newFamilyAdmin) {
		if (!repository.findByEmail(newFamilyAdmin.getEmail()).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "FamilyAdmin already exists for email %s".formatted(newFamilyAdmin.getEmail()));
		}
		return repository.save(newFamilyAdmin);
	}
}

