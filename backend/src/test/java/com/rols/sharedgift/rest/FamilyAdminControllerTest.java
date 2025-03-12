package com.rols.sharedgift.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rols.sharedgift.model.FamilyAdmin;
import com.rols.sharedgift.repository.FamilyAdminRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FamilyAdminController.class)
public class FamilyAdminControllerTest {

	List<FamilyAdmin> familieAdmins;

	@BeforeEach
	private void initializeRepository() {
		FamilyAdmin familyOne = FamilyAdmin.builder().name("One").email("one@email.com").password("onepwd").id(1L).build();
		FamilyAdmin familyTwo = FamilyAdmin.builder().name("Two").email("two@email.com").password("twopwd").id(2L).build();
		familieAdmins = new ArrayList<>();
		familieAdmins.add(familyOne);
		familieAdmins.add(familyTwo);
	}

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private FamilyAdminRepository repository;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void givenFamilies_whenGetFamilyById_thenReturnJsonArray() throws Exception {

		given(repository.findById(familieAdmins.get(0).getId())).willReturn(Optional.of(familieAdmins.get(0)));

		this.mvc.perform(get("/api/familyadmin/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value(familieAdmins.get(0).getName())).andDo(print());
	}
	
	@Test
	public void givenFamilies_whenGetFamilyById_thenReturnNoFamily() throws Exception {

		this.mvc.perform(get("/api/familyadmin/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
	
	@Test 
	public void whenPostFamilyAdmin_thenReturnJsonArray() throws Exception {
		FamilyAdmin newFamlily = familieAdmins.get(0).toBuilder().id(null).build();
		given(repository.save(newFamlily)).willReturn(familieAdmins.get(0));
		
		this.mvc.perform(post("/api/familyadmin").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newFamlily)))
				.andExpect(status().isCreated());
    }
	
	@Test
	public void whenPostFamilyAdmin_thenReturnConflict() throws Exception {
		FamilyAdmin newFamlily = familieAdmins.get(0);
		given(repository.findByEmail(newFamlily.getEmail())).willReturn(List.of(newFamlily));

		this.mvc.perform(post("/api/familyadmin").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newFamlily))).andExpect(status().isConflict());
	}
}
