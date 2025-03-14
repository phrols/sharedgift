package com.rols.sharedgift.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rols.sharedgift.family.Family;
import com.rols.sharedgift.family.FamilyRepository;
import com.rols.sharedgift.familyadmin.FamilyAdmin;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FamilyRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private FamilyRepository repository;
	
	@Test
	public void whenFindByName_thenReturnFamily() {
	    // given
		FamilyAdmin admin = FamilyAdmin.builder().name("toto").email("toto@sharedgift.com").password("admin").build(); 
		entityManager.persist(admin);
	    Family totoFamily = Family.builder().name("toto").admin(admin).build();
	    entityManager.persist(totoFamily);
	 
	    // when
	    Family found = repository.findByName(totoFamily.getName());
	 
	    // then
	    Assertions.assertEquals(found.getName(), totoFamily.getName());
	}

}
