package com.recruiter.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
		mvc.perform(MockMvcRequestBuilders.get("/candidate/invalid"))
		.andExpect(status().is4xxClientError())
		.andExpect(content().string(""));
	}

	@Test
	public void testGetCandidates() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.size()", greaterThan(25)));
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
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testAddCandidateNonExistsHeadhunter() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 0L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testAddCandidateEmptyJobTitle() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 1L, null, false));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testAddCandidateNonExistsJobTitle() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAdd", 1L, null, false));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testAddCandidateDuplicate() throws Exception {
		final String candidateJson = json(new Candidate("TestCandidateAddDuplicate", 1L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateAddDuplicate")));
		
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
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
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateCandidateDoesNotExists() throws Exception {
		final String candidateJson = json(new Candidate(0L, "TestCandidateUpdate", 1L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateCandidateEmptyHeadhunter() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", null, 1L, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateCandidateNonExistsHeadhunter() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", 0L, 1L, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateCandidateEmptyJobTitle() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", 1L, null, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateCandidateNonExistsJobTitle() throws Exception {
		final String candidateJson = json(new Candidate(1L, "TestCandidateUpdate", 1L, 0L, false));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateCandidateCreatedDateUnChanged() throws Exception {
		final Candidate candidate = new Candidate(5L, "TestCandidateUpdateDateUnChanged", 1L, 1L, false);
		final Date invalidDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
		candidate.setCreatedDate(invalidDate);
		final String candidateJson = json(candidate);
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		
		mvc.perform(MockMvcRequestBuilders.get("/candidate/5"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.createdDate", not(invalidDate.getTime())));
	}
	
	@Test
	public void testDeleteCandidateNotExists() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/candidate/0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is5xxServerError());
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
