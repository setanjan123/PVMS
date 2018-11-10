package com.pvms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.List;

import com.pvms.bean.Country;
import com.pvms.bean.Visa;
import com.pvms.bean.Transaction;
import com.pvms.bean.VisaCancelRequirements;
import com.pvms.util.DbUtil;
import com.pvms.util.Queries;

public class VisaDao {
	
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
	
	
	public void getDetails(List<Country> ct) throws Exception
	{
		Connection con=DbUtil.getConnection();
		PreparedStatement ps=con.prepareStatement(Queries.getCountries);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			String id=rs.getString(1);
			String name=rs.getString(2);
			double visa_cost=rs.getDouble(3);
			ct.add(new Country(id,name,visa_cost));
		}
		con.close();
		
			
	}
	
	public String getOccupation(String username) throws Exception
	{
		Connection con=DbUtil.getConnection();
		String occupation="";
		PreparedStatement ps=con.prepareStatement(Queries.getOccup);
		ps.setString(1, username);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
		 occupation=rs.getString(1);
		}
		con.close();
		return occupation;
	}
	public Date getExpDate(String username) throws Exception
	{
		Connection con=DbUtil.getConnection();
		Date d = null;
		PreparedStatement ps=con.prepareStatement(Queries.getPassportExpiry);
		ps.setString(1, username);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			d=rs.getDate(1);
		}
		con.close();
		return d;
		
	}
	
	public String submitVisaDetails(Visa nv,Transaction tdn) throws Exception
	{
		Connection con = DbUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(Queries.getNextVisaSeq);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int id = rs.getInt(1);
		String visa_Id ="VISA-"+id;
		ps=con.prepareStatement(Queries.getPassportId);
		ps.setString(1,nv.getUsername());
		ResultSet rs1=ps.executeQuery();
		String pass_id = null;
		while(rs1.next()){
			pass_id=rs1.getString(1);
		}
		ps=con.prepareStatement(Queries.addNewVisa);
		ps.setString(1,visa_Id);
		ps.setDate(2,nv.getDateOfIssue());
		ps.setDate(3,nv.getDateOfExpiry());
		ps.setString(4,nv.getCountry().getId());
		ps.setString(5,nv.getVisaType());
		ps.setString(6,pass_id);
		ps.setString(7,nv.getUsername());
		ps.setString(8,tdn.getId());
		int m=ps.executeUpdate();
		con.close();
		if(m>0)
			return visa_Id;
		else
			return null;
		
		
		
	}
	
	public boolean CancelVisa(String visa_id)
	{
		try{  
			    Connection con=DbUtil.getConnection();
				PreparedStatement ps=con.prepareStatement(Queries.cancelVisa);
				con.setAutoCommit(false);
				ps.setString(1,visa_id);
				int n=ps.executeUpdate();
				if(n<0)
				{
					con.rollback();
					con.close();
					return false;
			    }
				con.commit();
				con.close();
				return true;  
		} 
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
			   
	}
	
	
	public VisaCancelRequirements getCostDetails(Visa v) throws Exception
	{
		Connection con=DbUtil.getConnection();
		VisaCancelRequirements cdto=new VisaCancelRequirements();
		PreparedStatement ps=con.prepareStatement(Queries.visa_months_to_expiry);
		ps.setString(1,v.getVid());
		ResultSet rs=ps.executeQuery();
		if(!rs.next())
		return null;
		cdto.setDiff_months(rs.getInt(1));
		ps=con.prepareStatement(Queries.getOccup);
		ps.setString(1,v.getUsername());
		rs=ps.executeQuery();
		if(!rs.next())
		{
		con.close();
		return null;
		}
		cdto.setOccup(rs.getString(1));
		con.close();
		return cdto;
	}
	
	public void getVisas(List<Visa> visas,String uname) throws Exception
	{
		Connection con=DbUtil.getConnection();
		PreparedStatement ps=con.prepareStatement(Queries.getVisastoCancel);
		ps.setString(1,uname);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{   Country c=new Country();
		    c.setId(rs.getString(2));
			visas.add(new Visa(rs.getString(1),c,rs.getString(3),new Date(rs.getDate(4).getTime()),new Date(rs.getDate(5).getTime()),uname));
		}
		con.close();
		
		
	}
	
	
	
}
