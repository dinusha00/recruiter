package com.recruiter.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Fee.findByJobtitleid", query = "SELECT f FROM Fee f WHERE f.jobtitleid = ?1") })
@Table(name = "fee")
public class Fee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private int jobtitleid;

	@Column(nullable = false)
	private int type;

	@Column(nullable = false)
	private int count;

	@Column(nullable = false)
	private double amount;

	protected Fee() {
	}

	public Fee(final Long id, final int jobtitleid, final int type, final int count, final double amount) {
		this.id = id;
		this.jobtitleid = jobtitleid;
		this.type = type;
		this.count = count;
		this.amount = amount;
	}

	public Fee(final int jobtitleid, final int type, final int count, final double amount) {
		this.jobtitleid = jobtitleid;
		this.type = type;
		this.count = count;
		this.amount = amount;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Fee [id=");
		builder.append(id);
		builder.append(", jobtitleid=");
		builder.append(jobtitleid);
		builder.append(", type=");
		builder.append(type);
		builder.append(", count=");
		builder.append(count);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

	public int getJobtitleid() {
		return jobtitleid;
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
