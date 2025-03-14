package com.rols.sharedgift.auth;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rols.sharedgift.familyadmin.FamilyAdmin;
import com.rols.sharedgift.familyadmin.FamilyAdminRepository;

@Service
public class FamilyAdminUserDetailsService implements UserDetailsService {

	private final FamilyAdminRepository familyAdminRepository;

	FamilyAdminUserDetailsService(FamilyAdminRepository familyAdminRepository) {
		this.familyAdminRepository = familyAdminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
		FamilyAdmin user = familyAdminRepository.findByEmail(useremail)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + useremail));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				Collections.emptyList());
	}
}