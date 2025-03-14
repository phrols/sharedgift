package com.rols.sharedgift.player;



import java.util.Objects;

import com.rols.sharedgift.family.Family;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="player")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
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


	@Override
	public int hashCode() {
		return Objects.hash(email);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(email, other.email);
	}
}
