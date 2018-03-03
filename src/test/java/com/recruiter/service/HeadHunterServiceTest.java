package com.recruiter.service;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HeadHunterServiceTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetHeadHunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/headhunter").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling HeadHunterService.getHeadHunter")));
	}

	@Test
	public void testAddHeadHunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/headhunter").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling HeadHunterService.addHeadHunter")));
	}

	@Test
	public void testUpdateHeadHunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/headhunter").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling HeadHunterService.updateHeadHunter")));
	}

	@Test
	public void testDeleteHeadHunter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/headhunter").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling HeadHunterService.deleteHeadHunter")));
	}
}
