package com.pvms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pvms.bean.ForgotPassDetail;
import com.pvms.bean.LoginDetail;
import com.pvms.util.DbUtil;
import com.pvms.util.Queries;

public class LoginDao {
	
	public boolean validateUser(LoginDetail ld) throws Exception
	{
	    Connection con=DbUtil.getConnection();
		PreparedStatement ps=con.prepareStatement(Queries.login);
		ps.setString(1,ld.getUname());
		ps.setString(2,ld.getPass());
		boolean result=false;
		ResultSet rs=ps.executeQuery();
		result=rs.next();
		con.close();
		return result;
	}
	
	public ForgotPassDetail getDetail(String userName) throws Exception
	{
		Connection con=DbUtil.getConnection();
		ForgotPassDetail fsd=new ForgotPassDetail();
		PreparedStatement ps=con.prepareStatement(Queries.get_HQnA);
		ps.setString(1, userName);
		ResultSet rs1=ps.executeQuery();
		if(!rs1.next())
		return null;
		fsd.setHques(rs1.getString(1));
		fsd.setHans(rs1.getString(2));
		ps=con.prepareStatement(Queries.getPassword);
		ps.setString(1,userName);
		rs1=ps.executeQuery();
		rs1.next();
		fsd.setPass(rs1.getString(1));
		con.close();
		return fsd;
		
		
	}
	

}
