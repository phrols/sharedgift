package com.rols.sharedgift.familyadmin;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class FamilyAdminController {

	private final FamilyAdminRepository repository;
	
	private final PasswordEncoder passwordEncoder;

	FamilyAdminController(FamilyAdminRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Get a unique family admin
	 * @param id
	 * @return
	 */
	@GetMapping("/familyadmin/{id}")
	FamilyAdmin one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FamilyAdmin not found for id %d".formatted(id)));
	}
	
	@GetMapping("/familyadmin/me")
	FamilyAdmin me() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName();
	    return repository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FamilyAdmin not found for email %s".formatted(email)));
	}

	/**
	 * Not connected API to create a new family admin 
	 * TODO: move to the auth controller ?)
	 * @param newFamilyAdmin
	 * @return
	 */
	@PostMapping("/familyadmin")
	@ResponseStatus(HttpStatus.CREATED)
	FamilyAdmin newFamilyAdmin(@RequestBody FamilyAdmin newFamilyAdmin) {
		repository.findByEmail(newFamilyAdmin.getEmail()).ifPresent(familyAdmin -> {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "FamilyAdmin already exists for email %s".formatted(newFamilyAdmin.getEmail()));
		});
		return repository.save(newFamilyAdmin.toBuilder().password(passwordEncoder.encode(newFamilyAdmin.getPassword())).build());
	}
}

