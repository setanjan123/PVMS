
package com.pvms.bean;

public class VisaCancelRequirements {
	
	public VisaCancelRequirements(String occup, int diff_months) {
		super();
		this.occup = occup;
		this.diff_months = diff_months;
	}
	public VisaCancelRequirements() {
		// TODO Auto-generated constructor stub
	}
	private String occup;
	private int diff_months;
	public String getOccup() {
		return occup;
	}
	public void setOccup(String occup) {
		this.occup = occup;
	}
	public int getDiff_months() {
		return diff_months;
	}
	public void setDiff_months(int diff_months) {
		this.diff_months = diff_months;
	}
	

}
