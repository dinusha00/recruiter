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
		.andExpect(jsonPath("$.size()", greaterThan(27)));
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
	public void testAddCandidateEmptyHeadhunter() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", null, 1L, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("empty headhunter id when creating candidate, did not return exception", error);
	}
	
	@Test
	public void testAddCandidateNonExistsHeadhunter() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 0L, 1L, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("non exists headhunter id when creating candidate, did not return exception", error);
	}
	
	@Test
	public void testAddCandidateEmptyJobTitle() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 1L, null, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("empty jobtitle id when creating candidate, did not return exception", error);
	}
	
	@Test
	public void testAddCandidateNonExistsJobTitle() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 1L, null, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("non exists jobtitle id when creating candidate, did not return exception", error);
	}
	
	@Test
	public void testAddCandidateDuplicate() throws Exception {
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
		final String candidateJson = json(new Candidate(3L, "TestCandidateUpdate", 1L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateUpdate")))
		.andExpect(jsonPath("$.recruited", is(false)));
	}
	
	@Test
	public void testUpdateCandidateDuplicate() throws Exception {
		final String candidateJson = json(new Candidate(4L, "Arnold", 1L, 1L, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("duplicate candidate update did not return exception on duplicate attempt", error);
	}
	
	@Test
	public void testUpdateCandidateDoesNotExists() throws Exception {
		final String candidateJson = json(new Candidate(0L, "TestCandidateUpdate", 1L, 1L, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig candidate that does not exists, did not return an exception", error);
	}
	
	@Test
	public void testUpdateCandidateEmptyHeadhunter() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", null, 1L, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig candidate without headhunter id, did not return an exception", error);
	}
	
	@Test
	public void testUpdateCandidateNonExistsHeadhunter() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", 0L, 1L, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig candidate with non exists headhunter id, did not return an exception", error);
	}
	
	@Test
	public void testUpdateCandidateEmptyJobTitle() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", 1L, null, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig candidate without jobtitle id, did not return an exception", error);
	}
	
	@Test
	public void testUpdateCandidateNonExistsJobTitle() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", 1L, 0L, false));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig candidate with non exists jobtitle id, did not return an exception", error);
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
	
	@Test
	public void testRecruite() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate/2/recruite"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(2)))
		.andExpect(jsonPath("$.name", is("Thomson")))
		.andExpect(jsonPath("$.recruited", is(true)));
	}
	
	@Test
	public void testReject() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate/2/reject"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(2)))
		.andExpect(jsonPath("$.name", is("Thomson")))
		.andExpect(jsonPath("$.recruited", is(false)));
	}
}
