package com.rols.sharedgift.model;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="player")
@Data
@Builder
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Id;
	
	String firstname;
	
	String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_id")
	Family family;

	
	@Transient
	public boolean isInFamily(String familyId) {
	    boolean isInFamily = false;
	    if (this.getFamily()!=null) {
	    	isInFamily = this.getFamily().getId().equals(Long.parseLong(familyId));
	    }
	    return isInFamily;
	}
}
