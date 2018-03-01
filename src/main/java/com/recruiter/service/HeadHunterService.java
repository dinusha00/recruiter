package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadHunterService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/headhunter")
	public String index() {
		logger.info("calling HeadHunterService");
		return "calling HeadHunterService";
	}
}
