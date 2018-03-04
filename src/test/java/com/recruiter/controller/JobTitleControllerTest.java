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
		.andExpect(content().string(""))
		;
	}

	@Test
	public void testGetJobTitles() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
//		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].name", is("Mason")))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].name", is("Carpenter")))
		.andExpect(jsonPath("$[2].id", is(3)))
		.andExpect(jsonPath("$[2].name", is("Driver")))
		;
	}

	@Test
	public void testGetJobTitle() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Mason")))
		;
	}

	@Test
	public void testGetJobTitleNotAvailable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle/0"))
		.andExpect(status().isOk())
		.andExpect(content().string(""))
		;
	}

	@Test
	public void testAddJobTitle() throws Exception {
		final String jobTitleJson = json(new JobTitle("TestJobTitleAdd"));
		mvc.perform(MockMvcRequestBuilders.post("/jobtitle").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(jobTitleJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestJobTitleAdd")))
		;
	}
	
	@Test
	public void testUpdateJobTitle() throws Exception {
		final String jobTitleJson = json(new JobTitle("TestJobTitleUpdate"));
		mvc.perform(MockMvcRequestBuilders.put("/jobtitle").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(jobTitleJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestJobTitleUpdate")))
		;
	}
	
	 @Test
	 @Ignore(value="correct the test cases that will fail when data deletes and enable this test")
	 public void testDeleteJobTitle() throws Exception {
	 mvc.perform(MockMvcRequestBuilders.delete("/jobtitle/1").contentType(MediaType.APPLICATION_JSON))
	 .andExpect(status().isOk())
	 ;
	 }
}
