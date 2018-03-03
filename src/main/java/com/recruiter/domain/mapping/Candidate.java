package com.recruiter.domain.mapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(nullable = true)
	private Headhunter headhunter;

	protected Candidate() {
	}

	public Candidate(final String name, final Headhunter headhunter) {
		this.name = name;
		this.headhunter = headhunter;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Candidate [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", headhunter=");
		builder.append(headhunter);
		builder.append("]");
		return builder.toString();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@ManyToOne
	@JoinColumn(name = "headhunter_id")
	public Headhunter getHeadhunter() {
		return headhunter;
	}
}
