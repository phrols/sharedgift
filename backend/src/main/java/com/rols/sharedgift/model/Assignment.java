package com.rols.sharedgift.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="assignment")
@Data
@Builder
public class Assignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@OneToOne 
    @JoinColumn( name="from_player", nullable=false )
	Player source;
	
	@OneToOne 
    @JoinColumn( name="to_player", nullable=false )
	Player destination;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
	Event event;
}
