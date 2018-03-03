package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.common.ServiceBase;

@RestController
@RequestMapping("/recruitment")
public class RecruitmentService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String getRecruitment() {
		logger.info("calling RecruitmentService.getRecruitment");
		return "calling RecruitmentService.getRecruitment";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String confirmRecruitment() {
		logger.info("calling RecruitmentService.confirmRecruitment");
		return "calling RecruitmentService.confirmRecruitment";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String addRecruitment() {
		logger.info("calling RecruitmentService.addRecruitment");
		return "calling RecruitmentService.addRecruitment";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String dismissRecruitment() {
		logger.info("calling RecruitmentService.dismissRecruitment");
		return "calling RecruitmentService.dismissRecruitment";
	}
}
