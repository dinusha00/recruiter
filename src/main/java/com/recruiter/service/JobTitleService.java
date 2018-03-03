package com.recruiter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.mapping.JobTitle;
import com.recruiter.domain.repository.JobTitleRepository;

@RestController
@RequestMapping("/jobtitle")
public class JobTitleService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobTitleRepository jobTitleRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<JobTitle> getJobTitles() {
		logger.info("calling JobTitleService.getJobTitles");
		final List<JobTitle> jobTitles = jobTitleRepository.findAll();
		logger.info("returning from JobTitleService.getJobTitles jobTitles.size():{}", jobTitles.size());
		return jobTitles;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public JobTitle getJobTitle(@PathVariable final Long id) {
		logger.info("calling JobTitleService.getJobTitle id:" + id);
		final JobTitle jobTitle = jobTitleRepository.findOne(id);
		logger.info("returning from JobTitleService.getJobTitle jobTitle:{}", jobTitle);
		return jobTitle;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public JobTitle addJobTitle(@RequestBody final JobTitle jobTitle) {
		logger.info("calling JobTitleService.addJobTitle jobTitle:{}", jobTitle);
		final JobTitle createdJobTitle = jobTitleRepository.save(jobTitle);
		return createdJobTitle;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public JobTitle updateJobTitle(@RequestBody final JobTitle jobTitle) {
		logger.info("calling JobTitleService.updateJobTitle jobTitle:{}", jobTitle);
		final JobTitle updatedJobTitle = jobTitleRepository.save(jobTitle);
		return updatedJobTitle;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteJobTitle(@PathVariable final Long id) {
		logger.info("calling JobTitleService.deleteJobTitle id:{}", id);
		jobTitleRepository.delete(id);
	}
}
