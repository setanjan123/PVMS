package com.pvms.bean;

import java.sql.Date;

public class Passport {
	public Passport(String id, String country, String state, String city, Long pin, String servicetype,
			String booklettype, String username, Date issuedate, Date expirydate, String status, String psk) {
		super();
		this.id = id;
		this.country = country;
		this.state = state;
		this.city = city;
		this.pin = pin;
		this.servicetype = servicetype;
		this.booklettype = booklettype;
		this.username = username;
		this.issuedate = issuedate;
		this.expirydate = expirydate;
		this.status = status;
		this.psk = psk;
	}

	private
	String id;
	String country;
	String state;
	String city;
	Long pin;
	String servicetype;
	String booklettype;
	String username;
	Date issuedate;
	Date expirydate;
	String status;
	String psk;
	public Passport() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getPin() {
		return pin;
	}
	public void setPin(Long pin) {
		this.pin = pin;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getBooklettype() {
		return booklettype;
	}
	public void setBooklettype(String booklettype) {
		this.booklettype = booklettype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(java.util.Date issuedate) {
		this.issuedate = new Date(issuedate.getTime());
	}
	public Date getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(java.util.Date expirydate) {
		this.expirydate = new Date(expirydate.getTime());
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getPsk() {
		return psk;
	}

	public void setPsk(String psk) {
		this.psk = psk;
	}
	
	
}
