package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.base.ServiceBase;

@RestController
@RequestMapping("/headhunter")
public class HeadHunterService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String getHeadHunter() {
		logger.info("calling HeadHunterService.getHeadHunter");
		return "calling HeadHunterService.getHeadHunter";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addHeadHunter() {
		logger.info("calling HeadHunterService.addHeadHunter");
		return "calling HeadHunterService.addHeadHunter";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String updateHeadHunter() {
		logger.info("calling HeadHunterService.updateHeadHunter");
		return "calling HeadHunterService.updateHeadHunter";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteHeadHunter() {
		logger.info("calling HeadHunterService.deleteHeadHunter");
		return "calling HeadHunterService.deleteHeadHunter";
	}
}
