package com.pvms.util;

public interface Queries {
	
	String login="select * from login where username=? and password=?";
	String get_HQnA="select hint_ques,hint_ans from ques_ans where username=?";
	String getPassword="select password from login where username=?";
	String getNextUserSeq="select userseq.nextval from dual";
	String Register1="insert into registration values(?,?,?,?,?,?,?,?,?,?)";
	String Register2="insert into ques_ans values(?,?,?)";
	String Register3="insert into login values(?,?)";
	String getStates = "select * from state";
	String getCities = "select * from city";
	String getPSK = "select * from psk";
	String getNextPassSeq = "select pass_id.nextval from dual";
	String addNewPassport = "insert into passport_details values(?,?,?,?,?,?,?,?,?,?,'Active',?,?)";
	String updatePassport ="update passport_details set state=?,city=?,psk=?,issue_date=?,expiry_date=?,txn_id=? where username=?";
	String getPassportId="select pid from passport_details where username=?";
	String createTransaction= "insert into transaction values(?,?,?,?)";
	String getCountries="select * from Country where name!='India'";
	String getOccup="select occupation from registration where username=?";
	String getPassportExpiry="select expiry_date from passport_details where username=?";
	String getNextVisaSeq = "select visa_id.nextval from dual";
	String addNewVisa="insert into visa_details values(?,?,?,?,?,?,'Active',?,?)";
	String cancelVisa="update visa_details set status='cancelled' where vid=?";
	String visa_months_to_expiry="select months_between(date_of_expiry,sysdate) from visa_details where vid=?";
	String getVisastoCancel="select vid,country,visa_type,date_of_issue,date_of_expiry from visa_details where username=? and status='Active'";
	String getUserDetails="select * from registration where username=?";
	String getPassportDetails="select * from passport_details where username=?";
	String getVisaDetails="select * from visa_details where username=? and status='Active'";
	String updateUserDetails="update registration set first_name=?,last_name=?,dob=?,addr=?,contact_no=?,email=?,qualification=?,occupation=? where username=?";
}
