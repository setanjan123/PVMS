package com.pvms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;

import com.pvms.bean.City;
import com.pvms.bean.Passport;
import com.pvms.bean.PSK;
import com.pvms.bean.State;
import com.pvms.bean.Transaction;
import com.pvms.util.DbUtil;
import com.pvms.util.Queries;

public class PassportDao {

	
	public void getDetails(List<City>cities,List<State>states,List<PSK>psk) throws Exception
	{
	
		
		Connection con= DbUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(Queries.getStates);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			String id = rs.getString(1);
			String name = rs.getString(2);
			String country=rs.getString(3);
			states.add(new State(id,name,country));
		}
		
		ps = con.prepareStatement(Queries.getCities);
		rs = ps.executeQuery();
		while(rs.next())
		{
			String id = rs.getString(1);
			String name = rs.getString(2);
			String state_id = rs.getString(3);
			
			cities.add(new City(id,name,state_id));
		}
		
		ps = con.prepareStatement(Queries.getPSK);
		rs = ps.executeQuery();
		while(rs.next())
		{
			String id = rs.getString(1);
			String name = rs.getString(2);
			String city_id = rs.getString(3);
			psk.add(new PSK(id,name,city_id));
		}
		con.close();
		
		
	}

	

	public String submitPassDetails(Passport np,Transaction t) throws Exception {
		// TODO Auto-generated method stub
		Connection con = DbUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(Queries.getNextPassSeq);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int id = rs.getInt(1);
		String pass_id = "";
		if(np.getBooklettype().equals("30"))
		{
			pass_id = "PASS30-"+id;
		}
		else
		{
			pass_id = "PASS60-"+id;
		}
		ps=con.prepareStatement(Queries.addNewPassport);
		ps.setString(1,pass_id);
		ps.setString(2,"IND");
		ps.setString(3,np.getState());
		ps.setString(4,np.getCity());
		ps.setLong(5,np.getPin());
		ps.setString(6,np.getServicetype());
		ps.setString(7,np.getBooklettype());
		ps.setString(8, np.getUsername());
		ps.setDate(9, np.getIssuedate());
		ps.setDate(10, np.getExpirydate());
		ps.setString(11,t.getId());
		ps.setString(12,np.getPsk());
		int n = ps.executeUpdate();
		con.close();
		if(n>0)
	    return pass_id;
		return null;
	}
	
	public boolean RenewPass(Passport np,Transaction t) throws Exception {
		// TODO Auto-generated method stub
		Connection con = DbUtil.getConnection();
		String state=np.getState();
		String city=np.getCity();
		String psk=np.getPsk();
		String tid=t.getId();
		Calendar curr=Calendar.getInstance();
		PreparedStatement ps=con.prepareStatement(Queries.updatePassport);
		ps.setString(1,state);
		ps.setString(2,city);
		ps.setString(3,psk);
		ps.setDate(4,new java.sql.Date(curr.getTimeInMillis()));
		curr.add(Calendar.YEAR,10);
		ps.setDate(5,new java.sql.Date(curr.getTimeInMillis()));
		ps.setString(6,tid);
		ps.setString(7, np.getUsername());
		int n = ps.executeUpdate();
		con.close();
		if(n>0)
	    return true;
		return false;
	}
	
	public String getPassId(String username) throws Exception
	{
	Connection con=DbUtil.getConnection();
	PreparedStatement ps=con.prepareStatement(Queries.getPassportId);
	ps.setString(1,username);
	ResultSet rs1=ps.executeQuery();
	String pass_id = null;
	while(rs1.next()){
	pass_id=rs1.getString(1);
	con.close();
	return pass_id;
	}
	con.close();
	return null;
	}

}
