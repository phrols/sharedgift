package com.rols.sharedgift.model;

import java.util.Date;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="event")
@Data
@Builder
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;
	
	@Column(name = "event_date")
	Date date;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_id")
	Family family;
	
	@Enumerated(EnumType.STRING)
	@JdbcType(value = PostgreSQLEnumJdbcType.class)
	@JoinColumn(name = "event_status")
	private EventStatus status;
}
