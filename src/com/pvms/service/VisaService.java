package com.pvms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.pvms.dao.VisaDao;
import com.pvms.bean.VisaCancelRequirements;
import com.pvms.bean.Transaction;
import com.pvms.bean.Country;
import com.pvms.bean.Visa;

public class VisaService {
	
	public String getPassId(String username) throws Exception
	{
		VisaDao avd=new VisaDao();
		String pass_Id=avd.getPassId(username);
	    return pass_Id;
		
	}
	public String getOccupation(String username) throws Exception
	{
		VisaDao avd=new VisaDao();
		String occupation=avd.getOccupation(username);
		return occupation;
		
	}
	
	public static void getDetails(List<Country> ct) throws Exception
	{
		VisaDao avd=new VisaDao();
		avd.getDetails(ct);
	}
	
	public Transaction getApplyCost (Visa nv) throws Exception
	{
		
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE,10);
		Date issueDate=c.getTime();
		Date expDate = null;
		VisaDao avd=new VisaDao();
		String occupation=avd.getOccupation(nv.getUsername());
		
		
		if(nv.getVisaType().equals("Student"))
		{
			
			c.add(Calendar.YEAR,2);
			expDate=c.getTime();
		}
		else if(nv.getVisaType().equals("Work"))
		{
			if(occupation.equals("Private Employee"))
			{
				c.add(Calendar.YEAR,3);
				expDate=c.getTime();
			}
			else if(occupation.equals("Government Employee"))
			{
				c.add(Calendar.YEAR,4);
				expDate=c.getTime();
			}
			else if(occupation.equals("Self Employed"))
			{
				c.add(Calendar.YEAR,1);
				 expDate=c.getTime();
				
			}
			else if(occupation.equals("Retired"))
			{
				c.add(Calendar.YEAR,1);
				expDate=c.getTime();
				
			}
		}
		else
		{
			c.add(Calendar.MONTH,6);
			expDate=c.getTime();
		}
		
		Date dt=avd.getExpDate(nv.getUsername());
		if(expDate.after(dt))
		{
			return null;
		}
		
		nv.setDateOfIssue(issueDate);
		nv.setDateOfExpiry(expDate);
		
		int a = (int)Math.round(Math.random()*89999999+10000000);
		String b = Integer.toString(a);
		String txnid = "TXN"+b;
		double amt=0.0;
		if(nv.getVisaType().equals("Student"))
		{
			amt=20000.0;
		}
		else if(nv.getVisaType().equals("Work"))
		{
			amt=30000.0;
		}
		else if(nv.getVisaType().equals("Tourist"))
		{
			amt=10000.0;
		}
		amt+=nv.getCountry().getVisa_cost();
		Transaction tdn=new Transaction(txnid,amt,"Apply Visa");
		return tdn;
	}
	 
	public String submitVisaDetails(Visa nv,Transaction tdn) throws Exception
	{
		VisaDao avd=new VisaDao();
		String visa_Id=avd.submitVisaDetails(nv, tdn);
		return visa_Id;
	}
	
	public Transaction getCancelCost(Visa v) throws Exception {
		VisaDao dao = new VisaDao();
		double cost = 0;
		Transaction t = new Transaction();
		t.setId("TXN" + (Math.random() * 89999999 + 10000000));
		VisaCancelRequirements obj = dao.getCostDetails(v);
		if (obj.getDiff_months() < 0)
		return null;
		String occup = obj.getOccup();
		switch (occup) {
		case "Student":
			if (obj.getDiff_months() < 6)
				cost = 70;
			else if (obj.getDiff_months() >= 6)
				cost = 150;
			break;
		case "Private Employee":
			if (obj.getDiff_months() < 6)
				cost = 100;
			else if (obj.getDiff_months() >= 6 && obj.getDiff_months() < 12)
				cost = 150;
			else if (obj.getDiff_months() > 12)
				cost = 200;
			break;
		case "Government Employee":
			if (obj.getDiff_months() < 6)
				cost = 100;
			else if (obj.getDiff_months() >= 6 && obj.getDiff_months() < 12)
				cost = 160;
			else if (obj.getDiff_months() > 12)
				cost = 220;
			break;
		case "Self Employed":
			if (obj.getDiff_months() < 6)
				cost = 100;
			else if (obj.getDiff_months() >= 6)
				cost = 200;
			break;
		case "Retired":
			if (obj.getDiff_months() < 6)
				cost = 150;
			else if (obj.getDiff_months() >= 6)
				cost = 250;
			break;

		}
        t.setType("Cancel Visa");
		t.setAmount(cost);
		return t;
	}

	public void getVisas(List<Visa> visas,String uname) throws Exception {
		VisaDao dao = new VisaDao();
		dao.getVisas(visas,uname);
	}

	public boolean CancelVisa(String visa_id)
	{
		VisaDao vdao=new VisaDao();
		return vdao.CancelVisa(visa_id);
	}

	
	

}
