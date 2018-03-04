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
import com.recruiter.domain.entity.Headhunter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HeadhunterControllerTest extends ServiceBaseTest{

	@Test
	public void testInvalidPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter-invalid"))
		.andExpect(status().is4xxClientError())
		.andExpect(content().string(""))
		;
	}

	@Test
	public void testGetHeadhunters() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
//		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].name", is("James")))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].name", is("Mark")))
		;
	}

	@Test
	public void testGetHeadhunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("James")))
		;
	}

	@Test
	public void testGetHeadhunterNotAvailable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter/0"))
		.andExpect(status().isOk())
		.andExpect(content().string(""))
		;
	}

	@Test
	public void testAddHeadhunter() throws Exception {
		final String headhunterJson = json(new Headhunter("TestHeadhunterAdd"));
		mvc.perform(MockMvcRequestBuilders.post("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestHeadhunterAdd")))
		;
	}
	
	@Test
	public void testUpdateHeadhunter() throws Exception {
		final String headhunterJson = json(new Headhunter("TestHeadhunterUpdate"));
		mvc.perform(MockMvcRequestBuilders.put("/headhunter").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(headhunterJson))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.name", is("TestHeadhunterUpdate")))
		;
	}
	
	 @Test
	 @Ignore(value="correct the test cases that will fail when data deletes and enable this test")
	 public void testDeleteHeadhunter() throws Exception {
	 mvc.perform(MockMvcRequestBuilders.delete("/headhunter/1").contentType(MediaType.APPLICATION_JSON))
	 .andExpect(status().isOk())
	 ;
	 }
}
