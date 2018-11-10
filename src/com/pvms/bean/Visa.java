package com.pvms.bean;

import java.sql.Date;

public class Visa {
	
	public Visa(String vid, Country country, String visaType, Date dateOfIssue, Date dateOfExpiry, String username) {
		super();
		this.username = username;
		this.country = country;
		this.visaType = visaType;
		this.dateOfIssue = dateOfIssue;
		this.dateOfExpiry = dateOfExpiry;
		this.vid = vid;
	}

	private 
	String username;
	Country country;
	String visaType;
	Date dateOfIssue;
	Date dateOfExpiry;
	String vid;
	
	public String getVid() {
		return vid;
	}


	public void setVid(String vid) {
		this.vid = vid;
	}


	public Visa() {
		super();
	}


	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(java.util.Date dateOfIssue) {
		this.dateOfIssue = new Date(dateOfIssue.getTime());
	}

	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(java.util.Date dateOfExpiry) {
		this.dateOfExpiry = new Date(dateOfExpiry.getTime());
	}
	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	
	
	
	

}

