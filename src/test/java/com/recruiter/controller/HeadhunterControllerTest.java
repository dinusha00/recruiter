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
import com.recruiter.domain.entity.Headhunter;
import com.recruiter.domain.entity.JobTitle;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HeadhunterControllerTest extends ServiceBaseTest{

	@Test
	public void testInvalidPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter-invalid"))
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
		
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.post("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("duplicate headhunter creation did not return exception on duplicate attempt", error);
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
	public void testUpdateHeadhunterDoesNotExists() throws Exception {
		final String headhunterJson = json(new JobTitle("TestHeadhunterUpdateNotExists"));
		boolean error = false;
		try{
			mvc.perform(MockMvcRequestBuilders.put("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson));
		}catch (final Exception e) {
			error = true;
		}
		assertTrue("updatig headhunter that does not exists, did not return an exception", error);
	}
	
	 @Test
	public void testDeleteHeadhunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/headhunter/5").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	 
	@Test
	public void testDeleteHeadhunterDoesNotExists() throws Exception {
		boolean error = false;
		try {
			mvc.perform(MockMvcRequestBuilders.delete("/headhunter/0").contentType(MediaType.APPLICATION_JSON));
		} catch (final Exception e) {
			error = true;
		}
		assertTrue("deleting headhunter that does not exists, did not return an exception", error);
	}
}
