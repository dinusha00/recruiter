package com.recruiter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruiter.base.ServiceBase;
import com.recruiter.domain.entity.JobTitle;
import com.recruiter.domain.repository.JobTitleRepository;

@Service
public class JobTitleService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobTitleRepository jobTitleRepository;

	public List<JobTitle> readJobTitles() {
		logger.info("calling JobTitleService.readJobTitles");
		final List<JobTitle> jobTitles = jobTitleRepository.findAll();
		logger.info("returning from JobTitleService.readJobTitles jobTitles:{}", jobTitles);
		return jobTitles;
	}

	public JobTitle readJobTitle(final Long id) {
		logger.info("calling JobTitleService.readJobTitle id:" + id);
		final JobTitle jobTitle = jobTitleRepository.findOne(id);
		logger.info("returning from JobTitleService.readJobTitle jobTitle:{}", jobTitle);
		return jobTitle;
	}

	public JobTitle createJobTitle(final JobTitle jobTitle) {
		logger.info("calling JobTitleService.createJobTitle jobTitle:{}", jobTitle);
		if (jobTitle == null || null == jobTitle.getName() || jobTitle.getName().isEmpty()) {
			throw new IllegalArgumentException(msgJobTitleCannotBeEmpty);
		} else if (jobTitleRepository.findByName(jobTitle.getName()) != null) {
			throw new IllegalArgumentException(msgJobTitleAlreadyExists);
		}
		final JobTitle createdJobTitle = jobTitleRepository.save(jobTitle);
		logger.info("returning from JobTitleService.createJobTitle createdJobTitle:{}", createdJobTitle);
		return createdJobTitle;
	}

	public JobTitle updateJobTitle(final JobTitle jobTitle) {
		logger.info("calling JobTitleService.updateJobTitle jobTitle:{}", jobTitle);
		final JobTitle updatedJobTitle = jobTitleRepository.save(jobTitle);
		logger.info("returning from JobTitleService.updateJobTitle updatedJobTitle:{}", updatedJobTitle);
		return updatedJobTitle;
	}

	public void deleteJobTitle(final Long id) {
		logger.info("calling JobTitleService.deleteJobTitle id:{}", id);
		jobTitleRepository.delete(id);
	}
}
