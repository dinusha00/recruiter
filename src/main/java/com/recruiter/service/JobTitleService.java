package com.recruiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.repository.JobTitleRepository;

@RestController
@RequestMapping("/jobtitle")
public class JobTitleService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobTitleRepository jobTitleRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String getJobTitle() {
		logger.info("calling JobTitleService.getJobTitle" + jobTitleRepository.count() + " msg:" + msgJobTitleNotFound);
		return "calling JobTitleService.getJobTitle";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addJobTitle() {
		logger.info("calling JobTitleService.addJobTitle");
		return "calling JobTitleService.addJobTitle";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String updateJobTitle() {
		logger.info("calling JobTitleService.updateJobTitle");
		return "calling JobTitleService.updateJobTitle";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteJobTitle() {
		logger.info("calling JobTitleService.deleteJobTitle");
		return "calling JobTitleService.deleteJobTitle";
	}
}
