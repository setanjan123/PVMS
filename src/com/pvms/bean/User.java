package com.pvms.bean;

import java.util.ArrayList;
import java.util.Date;

public class User {
	
	private
	String firstName;
	String lastName;
	java.sql.Date dob;
	String address;
	String contactNo;
	String email;
	String edu;
	String occup;
	String gender;
	String hques;
	String hans;
	Passport pass;
	ArrayList<Visa> visas;
	public Passport getP() {
		return pass;
	}


	public void setP(Passport pass) {
		this.pass = pass;
	}


	public ArrayList<Visa> getVisas() {
		return visas;
	}


	public void setVisas(ArrayList<Visa> visas) {
		this.visas = visas;
	}


	
	
	
	public User() {
		super();
	}


	public User(String firstName, String lastName, Date dob, String address, String contactNo, String email,
			String edu, String occup, String gender, String hques, String hans) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = new java.sql.Date(dob.getTime());
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
		this.edu = edu;
		this.occup = occup;
		this.gender = gender;
		this.hques = hques;
		this.hans = hans;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public java.sql.Date getDob() {
		return dob;
	}


	public void setDob(java.sql.Date dob) {
		this.dob = dob;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEdu() {
		return edu;
	}


	public void setEdu(String edu) {
		this.edu = edu;
	}


	public String getOccup() {
		return occup;
	}


	public void setOccup(String occup) {
		this.occup = occup;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getHques() {
		return hques;
	}


	public void setHques(String hques) {
		this.hques = hques;
	}


	public String getHans() {
		return hans;
	}


	public void setHans(String hans) {
		this.hans = hans;
	}


	@Override
	public String toString() {
		return "NewUser [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", address=" + address
				+ ", contactNo=" + contactNo + ", email=" + email + ", edu=" + edu + ", occup=" + occup + ", gender="+ gender+"]";
	}
	
	
	
	

}
