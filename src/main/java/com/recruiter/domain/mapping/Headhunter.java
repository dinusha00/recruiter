package com.recruiter.domain.mapping;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "headhunter")
public class Headhunter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@ElementCollection(targetClass = Candidate.class)
	private Set<Candidate> candidates;

	protected Headhunter() {
	}

	public Headhunter(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Headhunter [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", candidates=");
		builder.append(candidates);
		builder.append("]");
		return builder.toString();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "headhunter", cascade = CascadeType.ALL)
	public Set<Candidate> getCandidates() {
		return candidates;
	}
}
