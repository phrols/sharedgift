package com.rols.sharedgift.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="family")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Family {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Id;

	String name;

//	@JsonIgnore
//	@OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
//	Set<Player> players;
}
