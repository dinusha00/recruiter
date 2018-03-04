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
	public List<JobTitle> readJobTitles() {
		logger.info("calling JobTitleController.readJobTitles");
		final List<JobTitle> jobTitles = jobTitleService.readJobTitles();
		logger.info("returning from JobTitleController.readJobTitles jobTitles:", jobTitles);
		return jobTitles;
	}

	@GetMapping(value = "/{id}")
	public JobTitle readJobTitle(@PathVariable final Long id) {
		logger.info("calling JobTitleController.readJobTitle id:" + id);
		final JobTitle jobTitle = jobTitleService.readJobTitle(id);
		logger.info("returning from JobTitleController.readJobTitle jobTitle:{}", jobTitle);
		return jobTitle;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public JobTitle createJobTitle(@RequestBody final JobTitle jobTitle) {
		logger.info("calling JobTitleController.createJobTitle jobTitle:{}", jobTitle);
		final JobTitle createdJobTitle = jobTitleService.createJobTitle(jobTitle);
		logger.info("returning from JobTitleController.createJobTitle createdJobTitle:{}", createdJobTitle);
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
