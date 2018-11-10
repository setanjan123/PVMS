package com.pvms.service;

import java.util.Calendar;
import java.util.List;

import com.pvms.dao.PassportDao;
import com.pvms.bean.City;
import com.pvms.bean.Passport;
import com.pvms.bean.PSK;
import com.pvms.bean.State;
import com.pvms.bean.Transaction;

public class PassportService {


	public static void getDetails(List<City>cities,List<State>states,List<PSK>psk) throws Exception
	{
		PassportDao apd = new PassportDao();
		apd.getDetails(cities, states, psk);
		
	}
	
	
	public Transaction getApplyCost(Passport np) throws Exception
	{
		int a = (int)Math.round(Math.random()*89999999+10000000);
		String b = Integer.toString(a);
		String txnid = "TXN"+b;
		Double amt = (double) 0;
		if(np.getServicetype().equals("Tatkal"))
		{
			amt+=1000;
		}
		if(np.getBooklettype().equals("30"))
		{
			amt+=1500;
		}
		else
		{
			amt+=2000;
		}
		Calendar cal = Calendar.getInstance();
		np.setIssuedate(cal.getTime());
		cal.add(Calendar.YEAR, 10);
		np.setExpirydate(cal.getTime());
		Transaction tdn =  new Transaction(txnid,amt,"Apply Passport");
		return tdn;
		
	}
	
	public String submitPassDetails(Passport np,Transaction t) throws Exception
	{
	    PassportDao apd = new PassportDao();
	    return apd.submitPassDetails(np, t);
	    
	}
	
	public boolean getPassId(String username) throws Exception
	{
	PassportDao avd=new PassportDao();
	String s=avd.getPassId(username);
	    if(s==null)
	    return false;
	    else
	    return true;
	}
	
	public boolean RenewPass(Passport np,Transaction t) throws Exception
	{
		PassportDao prd=new PassportDao();
		return prd.RenewPass(np, t);
	}
	
	public Transaction getRenewalCost(Passport np) throws Exception
	{
		int a = (int)Math.round(Math.random()*89999999+10000000);
		String b = Integer.toString(a);
		String txnid = "TXN"+b;
		Double amt = (double) 0;
		if(np.getServicetype().equals("Tatkal"))
		{
			amt+=1000;
		}
		if(np.getBooklettype().equals("30 Pages"))
		{
			amt+=1500;
		}
		else
		{
			amt+=2000;
		}
		Transaction tdn =  new Transaction(txnid,amt,"Passport Renewal");
		return tdn;	
	}
	
	
}
