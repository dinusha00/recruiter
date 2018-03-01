package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.domain.JobTitleRepository;

@RestController
public class HeadHunterService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JobTitleRepository jobTitleRepository;

	@RequestMapping("/headhunter")
	public String index() {
		logger.info("calling HeadHunterService111");
		logger.info("size=="+jobTitleRepository.findAll().size());
		logger.info("calling HeadHunterService222");
		return "calling HeadHunterService";
	}
}
