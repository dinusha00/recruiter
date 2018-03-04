package com.recruiter.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.recruiter.domain.entity.JobTitle;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CandidateControllerTest extends ServiceBaseTest {

	@Test
	public void testInvalidPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate-invalid"))
		.andExpect(status().is4xxClientError())
		.andExpect(content().string(""));
	}

	@Test
	public void testGetCandidates() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.size()", greaterThan(44)));
	}

	@Test
	public void testGetCandidate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Arnold")))
		.andExpect(jsonPath("$.recruited", is(true)));
	}

	@Test
	public void testGetCandidateNotAvailable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate/0"))
		.andExpect(status().isOk())
		.andExpect(content().string(""));
	}

	@Test
	public void testAddCandidate() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 1L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateAdd")))
		.andExpect(jsonPath("$.recruited", is(false)));
	}
	
	@Test
	public void testUpdateCandidateDuplicate() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAddDuplicate", 1L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateAddDuplicate")));

		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("duplicate candidate creation did not return exception on duplicate attempt", error);
	}
	
	@Test
	public void testUpdateCandidate() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateUpdate", 1L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateUpdate")))
		.andExpect(jsonPath("$.recruited", is(false)));
	}
	
	@Test
	public void testUpdateCandidateDoesNotExists() throws Exception {
		final String candidateJson = json(new JobTitle("TestCandidateDoesNotExists"));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig candidate that does not exists, did not return an exception", error);
	}
	
	@Test
	public void testDeleteCandidate() throws Exception {
		boolean error = false;
		try {
			mvc.perform(MockMvcRequestBuilders.delete("/candidate/0").contentType(MediaType.APPLICATION_JSON));
		} catch (final Exception e) {
			error = true;
		}
		assertTrue("deleting candidate that does not exists, did not return an exception", error);
	}
}
