package com.pvms.bean;

public class Transaction {
	
	String id;
	Double amount;
	String type;
	public Transaction() {
		super();
	}
	public Transaction(String id, Double amount, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
