package com.recruiter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.JobTitle;
import com.recruiter.service.JobTitleService;

@RestController
@RequestMapping("/jobtitle")
public class JobTitleController extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobTitleService jobTitleService;

	@GetMapping
	public List<JobTitle> getJobTitles() {
		logger.info("calling JobTitleController.getJobTitles");
		final List<JobTitle> jobTitles = jobTitleService.getJobTitles();
		logger.info("returning from JobTitleController.getJobTitles jobTitles:", jobTitles);
		return jobTitles;
	}

	@GetMapping(value = "/{id}")
	public JobTitle getJobTitle(@PathVariable final Long id) {
		logger.info("calling JobTitleController.getJobTitle id:" + id);
		final JobTitle jobTitle = jobTitleService.getJobTitle(id);
		logger.info("returning from JobTitleController.getJobTitle jobTitle:{}", jobTitle);
		return jobTitle;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public JobTitle addJobTitle(@RequestBody final JobTitle jobTitle) {
		logger.info("calling JobTitleController.addJobTitle jobTitle:{}", jobTitle);
		final JobTitle createdJobTitle = jobTitleService.addJobTitle(jobTitle);
		logger.info("returning from JobTitleController.addJobTitle createdJobTitle:{}", createdJobTitle);
		return createdJobTitle;
	}

	@PutMapping
	public JobTitle updateJobTitle(@RequestBody final JobTitle jobTitle) {
		logger.info("calling JobTitleController.updateJobTitle jobTitle:{}", jobTitle);
		final JobTitle updatedJobTitle = jobTitleService.updateJobTitle(jobTitle);
		logger.info("returning from JobTitleController.updateJobTitle updatedJobTitle:{}", updatedJobTitle);
		return updatedJobTitle;
	}

	@DeleteMapping(value = "/{id}")
	public void deleteJobTitle(@PathVariable final Long id) {
		logger.info("calling JobTitleController.deleteJobTitle id:{}", id);
		jobTitleService.deleteJobTitle(id);
	}
}
