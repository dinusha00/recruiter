package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruitmentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/recruitment")
	public String index() {
		logger.info("calling RecruitmentService");
		return "calling RecruitmentService";
	}
}
