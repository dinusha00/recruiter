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
public class RecruitmentServiceTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetRecruitment() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/recruitment").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling RecruitmentService.getRecruitment")));
	}

	@Test
	public void testConfirmRecruitment() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/recruitment").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling RecruitmentService.confirmRecruitment")));
	}

	@Test
	public void testAddRecruitment() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/recruitment").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling RecruitmentService.addRecruitment")));
	}

	@Test
	public void testDismissRecruitment() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/recruitment").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("calling RecruitmentService.dismissRecruitment")));
	}
}
