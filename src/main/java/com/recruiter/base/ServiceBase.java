package com.recruiter.base;

import org.springframework.beans.factory.annotation.Value;

public class ServiceBase {

	@Value("${jobtitle.cannotbe.empty}")
	protected String msgJobTitleCannotBeEmpty;

	@Value("${jobtitle.already.exists}")
	protected String msgJobTitleAlreadyExists;

	@Value("${jobtitle.doesnot.exists}")
	protected String msgJobTitleDoesnotExists;

	@Value("${headhunter.cannotbe.empty}")
	protected String msgHeadhunterCannotBeEmpty;

	@Value("${headhunter.already.exists}")
	protected String msgHeadhunterAlreadyExists;

	@Value("${headhunter.doesnot.exists}")
	protected String msgHeadhunterDoesnotExists;

	@Value("${candidate.cannotbe.empty}")
	protected String msgCandidateCannotBeEmpty;

	@Value("${candidate.already.exists}")
	protected String msgCandidateAlreadyExists;

	@Value("${currency.code}")
	protected String currencyCode;

	@Value("${currency.format}")
	protected String currencyFormat;
}
