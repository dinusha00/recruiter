package com.recruiter.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.recruiter.base.ServiceBaseTest;
import com.recruiter.domain.entity.Candidate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CandidateControllerTest extends ServiceBaseTest{

	@Test
	public void testInvalidPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate-invalid"))
		.andExpect(status().is4xxClientError())
		.andExpect(content().string(""))
		;
	}

	@Test
	public void testGetCandidates() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
//		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].name", is("Virat Kohli")))
		.andExpect(jsonPath("$[0].recruited", is(false)))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].name", is("AB de Villiers")))
		.andExpect(jsonPath("$[1].recruited", is(false)))
		.andExpect(jsonPath("$[2].id", is(3)))
		.andExpect(jsonPath("$[2].name", is("Chris Gayle")))
		.andExpect(jsonPath("$[2].recruited", is(false)))
		.andExpect(jsonPath("$[3].id", is(4)))
		.andExpect(jsonPath("$[3].name", is("Rohit Sharma")))
		.andExpect(jsonPath("$[3].recruited", is(false)))
		.andExpect(jsonPath("$[4].id", is(5)))
		.andExpect(jsonPath("$[4].name", is("Lasith Malinga")))
		.andExpect(jsonPath("$[4].recruited", is(false)))
		;
	}

	@Test
	public void testGetCandidate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Virat Kohli")))
		.andExpect(jsonPath("$.recruited", is(false)))
		;
	}

	@Test
	public void testGetCandidateNotAvailable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate/0"))
		.andExpect(status().isOk())
		.andExpect(content().string(""))
		;
	}

	@Test
	public void testAddCandidate() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 1L, false));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateAdd")))
		.andExpect(jsonPath("$.recruited", is(false)))
		;
	}
	
	@Test
	public void testUpdateCandidate() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateUpdate", 1L, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateUpdate")))
		.andExpect(jsonPath("$.recruited", is(false)))
		;
	}
	
	 @Test
	 @Ignore(value="correct the test cases that will fail when data deletes and enable this test")
	 public void testDeleteCandidate() throws Exception {
	 mvc.perform(MockMvcRequestBuilders.delete("/candidate/1").contentType(MediaType.APPLICATION_JSON))
	 .andExpect(status().isOk())
	 ;
	 }
}
