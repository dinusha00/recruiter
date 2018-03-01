package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobTitleService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/jobtitle")
	public String index() {
		logger.info("calling JobTitleService");
		return "calling JobTitleService";
	}
}
