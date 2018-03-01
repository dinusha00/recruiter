package com.recruiter.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadHunterService {

	@RequestMapping("/headhunter")
	public String index() {
		return "calling HeadHunterService";
	}
}