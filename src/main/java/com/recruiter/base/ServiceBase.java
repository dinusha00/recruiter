package com.recruiter.base;

import org.springframework.beans.factory.annotation.Value;

public class ServiceBase {

	@Value("${jobtitle.cannotbe.empty}")
	protected String msgJobTitleCannotBeEmpty;

	@Value("${jobtitle.already.exists}")
	protected String msgJobTitleAlreadyExists;

	@Value("${headhunter.cannotbe.empty}")
	protected String msgHeadhunterCannotBeEmpty;

	@Value("${headhunter.already.exists}")
	protected String msgHeadhunterAlreadyExists;

	@Value("${candidate.cannotbe.empty}")
	protected String msgCandidateCannotBeEmpty;

	@Value("${candidate.already.exists}")
	protected String msgCandidateAlreadyExists;
}
