package com.pvms.bean;

import java.io.Serializable;

public class PSK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private
	String id;
	String name;
	String city_id;
	public PSK() {
		super();
	}
	public PSK(String id, String name, String city_id) {
		super();
		this.id = id;
		this.name = name;
		this.city_id = city_id;
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
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	
	
}
