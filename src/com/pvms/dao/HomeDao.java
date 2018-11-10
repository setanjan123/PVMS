package com.pvms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pvms.bean.Country;
import com.pvms.bean.Passport;
import com.pvms.bean.User;
import com.pvms.bean.Visa;
import com.pvms.util.DbUtil;
import com.pvms.util.Queries;

public class HomeDao {
	
	
	public User getDetails(String uname) throws Exception
	{
		Connection con=DbUtil.getConnection();
		PreparedStatement ps=con.prepareStatement(Queries.getUserDetails);
		ps.setString(1,uname);
		ResultSet rs=ps.executeQuery();
		rs.next();
		User u= new User();
		u.setFirstName(rs.getString(2));
		u.setLastName(rs.getString(3));
		u.setDob(rs.getDate(4));
		u.setAddress(rs.getString(5));
		u.setContactNo(rs.getString(6));
		u.setEmail(rs.getString(7));
		u.setEdu(rs.getString(8));
		u.setOccup(rs.getString(9));
		u.setGender(rs.getString(10));
		ps=con.prepareStatement(Queries.getPassportDetails);
		ps.setString(1,uname);
		rs=ps.executeQuery();
		ArrayList<Visa> visas=new ArrayList<Visa>();
		if(rs.next())
		{
			Passport pass=new Passport();
			pass.setId(rs.getString(1));
			pass.setIssuedate(rs.getDate(9));
			pass.setExpirydate(rs.getDate(10));
			u.setP(pass);
			ps=con.prepareStatement(Queries.getVisaDetails);
			ps.setString(1,uname);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Visa visa=new Visa();
				visa.setVid(rs.getString(1));
				visa.setDateOfIssue(rs.getDate(2));
				visa.setDateOfExpiry(rs.getDate(3));
				Country c=new Country();
				c.setId(rs.getString(4));
				visa.setCountry(c);
				visa.setVisaType(rs.getString(5));
				visas.add(visa);
			}
		}
		u.setVisas(visas);
		con.close();
		return u;
		
	}
	
	public void Update(User u,String uname) throws Exception
	{
		Connection con=DbUtil.getConnection();
		PreparedStatement ps=con.prepareStatement(Queries.updateUserDetails);
		ps.setString(1,u.getFirstName());
		ps.setString(2,u.getLastName());
		ps.setDate(3,u.getDob());
		ps.setString(4,u.getAddress());
		ps.setString(5,u.getContactNo());
		ps.setString(6,u.getEmail());
		ps.setString(7,u.getEdu());
		ps.setString(8,u.getOccup());
		ps.setString(9,uname);
		ps.executeUpdate();
		con.close();
		
	}

}
