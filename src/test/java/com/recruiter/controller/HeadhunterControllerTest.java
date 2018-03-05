package com.recruiter.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
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
import com.recruiter.domain.entity.Headhunter;
import com.recruiter.domain.entity.JobTitle;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HeadhunterControllerTest extends ServiceBaseTest{

	@Test
	public void testInvalidPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter/invalid"))
		.andExpect(status().is4xxClientError())
		.andExpect(content().string(""));
	}

	@Test
	public void testGetHeadhunters() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.size()", greaterThan(4)));
	}

	@Test
	public void testGetHeadhunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Wilson")));
	}

	@Test
	public void testGetHeadhunterNotAvailable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter/0"))
		.andExpect(status().isOk())
		.andExpect(content().string(""));
	}

	@Test
	public void testAddHeadhunter() throws Exception {
		final String headhunterJson = json(new Headhunter("TestHeadhunterAdd"));
		mvc.perform(MockMvcRequestBuilders.post("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestHeadhunterAdd")));
	}
	
	@Test
	public void testAddHeadhunterDuplicate() throws Exception {
		final String headhunterJson = json(new Headhunter("TestHeadhunterAddDuplicate"));
		mvc.perform(MockMvcRequestBuilders.post("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestHeadhunterAddDuplicate")));
		
		mvc.perform(MockMvcRequestBuilders.post("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateHeadhunter() throws Exception {
		final String headhunterJson = json(new Headhunter(2L, "TestHeadhunterUpdate"));
		mvc.perform(MockMvcRequestBuilders.put("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestHeadhunterUpdate")));
	}
	
	@Test
	public void testUpdateHeadhunterDuplicate() throws Exception {
		final String headhunterJson = json(new Headhunter(4L, "Wilson"));
		mvc.perform(MockMvcRequestBuilders.put("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testUpdateHeadhunterDoesNotExists() throws Exception {
		final String headhunterJson = json(new JobTitle("TestHeadhunterUpdateNotExists"));
		mvc.perform(MockMvcRequestBuilders.put("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().is5xxServerError());
	}
	
	 @Test
	public void testDeleteHeadhunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/headhunter/4").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	 
	@Test
	public void testDeleteHeadhunterDoesNotExists() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/headhunter/0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testGetHeadhunterCandidates() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter/1/candidates"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.size()", lessThan(19)));
	}
	
	@Test
	public void testCalculate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter/2/calculate"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.amount", is("2475.00")))
		.andExpect(jsonPath("$.currency", is("USD")));
	}
}
