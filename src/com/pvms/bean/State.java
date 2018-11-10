package com.pvms.bean;

import java.io.Serializable;

public class State implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private
	String id;
	String name;
    String country;
	public State() {
		super();
	}
	public State(String id, String name,String country) {
		super();
		this.id = id;
		this.name = name;
		this.country=country;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
