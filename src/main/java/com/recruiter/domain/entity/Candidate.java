package com.recruiter.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Long headhunterId;

	@Column(nullable = false)
	private Long jobTitleId;

	@Column(nullable = false)
	private Boolean recruited = false;

	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date createdDate;

	protected Candidate() {
	}

	public Candidate(final Long id, final String name, final Long headhunterId, final Long jobTitleId, final Boolean recruited) {
		this.id = id;
		this.name = name;
		this.headhunterId = headhunterId;
		this.jobTitleId = jobTitleId;
		this.recruited = recruited;
	}

	public Candidate(final String name, final Long headhunterId, final Long jobTitleId, final Boolean recruited) {
		this.name = name;
		this.headhunterId = headhunterId;
		this.jobTitleId = jobTitleId;
		this.recruited = recruited;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Candidate [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", headhunterId=");
		builder.append(headhunterId);
		builder.append(", jobTitleId=");
		builder.append(jobTitleId);
		builder.append(", recruited=");
		builder.append(recruited);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append("]");
		return builder.toString();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getHeadhunterId() {
		return headhunterId;
	}

	public Boolean getRecruited() {
		return recruited;
	}

	public void setRecruited(final Boolean recruited) {
		this.recruited = recruited;
	}

	public Long getJobTitleId() {
		return jobTitleId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}
}
