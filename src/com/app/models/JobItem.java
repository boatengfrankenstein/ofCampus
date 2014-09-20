package com.app.models;

public class JobItem {
	private String title;
	private String details;
	private String pubDate;
	private String type;
	private String subtype;
	private String company;
	private String experienceLevel;
	private String salaryRange;
	private String designation;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subtype;
	}

	public void setSubType(String subtype) {
		this.subtype = subtype;
	}
	
	public String getExperienceLevel() {
		return experienceLevel;
	}
	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public String getSalaryRange() {
		return salaryRange;
	}
	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

}