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
public class JobTitleServiceTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetJobTitle() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/jobtitle").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling JobTitleService.getJobTitle")));
	}

	@Test
	public void tetAddJobTitle() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/jobtitle").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling JobTitleService.addJobTitle")));
	}

	@Test
	public void testUpdateJobTitle() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/jobtitle").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling JobTitleService.updateJobTitle")));
	}

	@Test
	public void testDeleteJobTitle() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/jobtitle").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling JobTitleService.deleteJobTitle")));
	}
}
