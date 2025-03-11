package com.rols.sharedgift.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rols.sharedgift.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
