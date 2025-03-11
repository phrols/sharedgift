package com.rols.sharedgift.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="family")
@Data
@Builder
public class Family {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
	FamilyAdmin admin;

	@JsonIgnore
	@OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
	Set<Player> players;
	
	@Transient
	public int getPlayersNumber() {
		return players!= null ? players.size() : 0;
	}
}
