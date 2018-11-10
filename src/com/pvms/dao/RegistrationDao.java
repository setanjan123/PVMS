package com.pvms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import com.pvms.bean.LoginDetail;
import com.pvms.bean.User;
import com.pvms.util.DbUtil;
import com.pvms.util.Queries;

public class RegistrationDao {
	public LoginDetail submitUserDetails(User u) throws Exception{
		Connection con=DbUtil.getConnection();
		PreparedStatement ps=con.prepareStatement(Queries.getNextUserSeq);
		ResultSet rs=ps.executeQuery();
		rs.next();
		int num=rs.getInt(1);
		String userName=u.getFirstName().substring(0,3)+"-"+num;
		LocalDate ld=LocalDate.now();
		int date=ld.getDayOfMonth();
		int month=ld.getMonthValue();
		String s="@#$";
		int index=(int)(Math.random()*s.length());
		long lg=Math.round(Math.random()*899 +100);
		String pass=Integer.toString(date)+Integer.toString(month)+s.charAt(index)+lg;
		LoginDetail ld1=new LoginDetail(userName,pass);
		ps=con.prepareStatement(Queries.Register1);
		ps.setString(1,userName);
		ps.setString(2,u.getFirstName());
		ps.setString(3,u.getLastName());
		ps.setDate(4,u.getDob());
		ps.setString(5,u.getAddress());
		ps.setString(6,u.getContactNo());
		ps.setString(7,u.getEmail());
		ps.setString(8,u.getEdu());
		ps.setString(9,u.getOccup());
		ps.setString(10,u.getGender());
		int n=ps.executeUpdate();
		if(n>0)
		{
			ps=con.prepareStatement(Queries.Register2);
			ps.setString(1,userName);
			ps.setString(2,u.getHques());
			ps.setString(3,u.getHans());
			ps.executeUpdate();
			ps=con.prepareStatement(Queries.Register3);
			ps.setString(1,userName);
			ps.setString(2,pass);
			ps.executeUpdate();
			con.close();
			return ld1;
			
		}
		con.close();
		return null;
		
			
			
			
		}
		
		
		
		
		
		
		
	}

