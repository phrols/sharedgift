package com.rols.sharedgift.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rols.sharedgift.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
