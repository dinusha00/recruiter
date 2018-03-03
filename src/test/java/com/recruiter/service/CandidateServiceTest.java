package com.recruiter.service;

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
import com.recruiter.domain.mapping.Candidate;
import com.recruiter.domain.mapping.Headhunter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CandidateServiceTest extends ServiceBaseTest{

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
		.andExpect(jsonPath("$[0].name", is("Virat")))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].name", is("Rohith")))
		.andExpect(jsonPath("$[2].id", is(3)))
		.andExpect(jsonPath("$[2].name", is("Dawan")))
		;
	}

	@Test
	public void testGetCandidate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/candidate/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Virat")))
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
		final Headhunter headhunter = new Headhunter("TestHeadhunterAdd");
		final String candidateJson = json(new Candidate("TestCandidateAdd", headhunter));
		mvc.perform(MockMvcRequestBuilders.post("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateAdd")))
		;
	}
	
	@Test
	public void testUpdateCandidate() throws Exception {
		final Headhunter headhunter = new Headhunter("TestHeadhunterAdd");
		final String candidateJson = json(new Candidate("TestCandidateUpdate", headhunter));
		mvc.perform(MockMvcRequestBuilders.put("/candidate").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(candidateJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestCandidateUpdate")))
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
