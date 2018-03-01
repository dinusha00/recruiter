package com.recruiter.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobTitleService {

	@RequestMapping("/jobtitle")
	public String index() {
		return "calling JobTitleService";
	}
}