package com.learning.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tcresults")
public class TestResult implements Serializable {

	@Id
	@Column(name = "tcid", unique = true, nullable = false)
	private int tcid;

	@Column(name = "result", nullable = false)
	private String result;

	@Column(name = "build", nullable = false)
	private String build;

	public int getTcid() {
		return tcid;
	}

	public void setTcid(int tcid) {
		this.tcid = tcid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	@Override
	public String toString() {
		return tcid + "\t" + result + "\t" + build;
	}
}
