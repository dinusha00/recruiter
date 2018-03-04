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
import com.recruiter.domain.entity.JobTitle;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class JobTitleControllerTest extends ServiceBaseTest{

	@Test
	public void testInvalidPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle-invalid"))
		.andExpect(status().is4xxClientError())
		.andExpect(content().string(""));
	}

	@Test
	public void testGetJobTitles() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.size()", greaterThan(2)));
	}

	@Test
	public void testGetJobTitle() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Mason")));
	}

	@Test
	public void testGetJobTitleNotAvailable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle/0"))
		.andExpect(status().isOk())
		.andExpect(content().string(""));
	}

	@Test
	public void testAddJobTitle() throws Exception {
		final String jobTitleJson = json(new JobTitle("TestJobTitleAdd"));
		mvc.perform(MockMvcRequestBuilders.post("/jobtitle").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(jobTitleJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestJobTitleAdd")));
	}
	
	@Test
	public void testAddJobTitleDuplicate() throws Exception {
		final String jobTitleJson = json(new JobTitle("TestJobTitleAddDuplicate"));
		mvc.perform(MockMvcRequestBuilders.post("/jobtitle").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(jobTitleJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestJobTitleAddDuplicate")));
		
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.post("/jobtitle").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(jobTitleJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("duplicate job title creation did not return exception on duplicate attempt", error);
	}
	
	@Test
	public void testUpdateJobTitle() throws Exception {
		final String jobTitleJson = json(new JobTitle(2L, "CarpenterUpdate"));
		mvc.perform(MockMvcRequestBuilders.put("/jobtitle").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(jobTitleJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("CarpenterUpdate")));
	}
	
	@Test
	public void testUpdateJobTitleDoesNotExists() throws Exception {
		final String jobTitleJson = json(new JobTitle("TestJobTitleNotExists"));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/jobtitle").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(jobTitleJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig job title that does not exists, did not return an exception", error);
	}
	
	@Test
	public void testDeleteJobTitle() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/jobtitle/3").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteJobTitleDoesNotExists() throws Exception {
		boolean error = false;
		try {
			mvc.perform(MockMvcRequestBuilders.delete("/jobtitle/0").contentType(MediaType.APPLICATION_JSON));
		} catch (final Exception e) {
			error = true;
		}
		assertTrue("deleting job title that does not exists, did not return an exception", error);
	}
}
