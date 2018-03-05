package com.recruiter.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fee")
public class Fee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Long jobTitleId;

	@Column(nullable = false)
	private int type;

	@Column(nullable = false)
	private int count;

	@Column(nullable = false)
	private double amount;

	protected Fee() {
	}

	public Fee(final Long id, final Long jobTitleId, final int type, final int count, final double amount) {
		this.id = id;
		this.jobTitleId = jobTitleId;
		this.type = type;
		this.count = count;
		this.amount = amount;
	}

	public Fee(final Long jobTitleId, final int type, final int count, final double amount) {
		this.jobTitleId = jobTitleId;
		this.type = type;
		this.count = count;
		this.amount = amount;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Fee [id=");
		builder.append(id);
		builder.append(", jobTitleId=");
		builder.append(jobTitleId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", count=");
		builder.append(count);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

	public Long getJobTitleId() {
		return jobTitleId;
	}

	public Long getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public int getCount() {
		return count;
	}

	public double getAmount() {
		return amount;
	}
}
