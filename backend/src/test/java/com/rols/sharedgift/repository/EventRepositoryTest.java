package com.rols.sharedgift.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rols.sharedgift.model.Event;
import com.rols.sharedgift.model.EventStatus;
import com.rols.sharedgift.model.Family;
import com.rols.sharedgift.model.FamilyAdmin;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private EventRepository repository;
	
	@Test
	public void givenNewEvent_whenSave_thenSuccess() {
	    // given
		FamilyAdmin admin = FamilyAdmin.builder().name("toto").email("toto@sharedgift.com").password("admin").build(); 
		entityManager.persist(admin);
	    Family totoFamily = Family.builder().name("toto").admin(admin).build();
	    entityManager.persist(totoFamily);
	    
	    Date eventDate = new DateTime(2025, 3, 11, 16, 8).toDate();
	    
	    Event newEvent = Event.builder().name("toto").date(eventDate).family(totoFamily).status(EventStatus.NEW).build();
	 
	    // when
	    Event insertedEvent = repository.save(newEvent);
	 
	    // then
	    assertThat(entityManager.find(Event.class, insertedEvent.getId()) ).isEqualTo(newEvent);
	}

}
