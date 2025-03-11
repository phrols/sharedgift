package com.rols.sharedgift.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rols.sharedgift.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}