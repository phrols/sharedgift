package com.rols.sharedgift.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rols.sharedgift.model.Family;

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
	    Family toto = Family.builder().name("toto").build();
	    entityManager.persist(toto);
	    entityManager.flush();
	 
	    // when
	    Family found = repository.findByName(toto.getName());
	 
	    // then
	    Assertions.assertEquals(found.getName(), toto.getName());
	}

}
