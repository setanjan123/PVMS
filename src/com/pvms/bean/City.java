package com.pvms.bean;

public class City {
	private
	String id;
	String name;
	String state_id;
	public City(String id, String name, String state_id) {
		super();
		this.id = id;
		this.name = name;
		this.state_id = state_id;
	}
	public City() {
		super();
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
	public String getState_id() {
		return state_id;
	}
	public void setState_id(String state_id) {
		this.state_id = state_id;
	}
	
}
