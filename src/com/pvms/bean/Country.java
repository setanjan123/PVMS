package com.pvms.bean;

public class Country {
	private
	String id;
	String name;
	double visa_cost;
	public double getVisa_cost() {
		return visa_cost;
	}
	public void setVisa_cost(double visa_cost) {
		this.visa_cost = visa_cost;
	}
	public Country() {
		super();
	}
	public Country(String id, String name,double visa_cost) {
		super();
		this.id = id;
		this.name = name;
		this.visa_cost=visa_cost;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
