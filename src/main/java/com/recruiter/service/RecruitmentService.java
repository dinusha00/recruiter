package com.recruiter.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruitmentService {

	@RequestMapping("/recruitment")
	public String index() {
		return "calling RecruitmentService";
	}
}
