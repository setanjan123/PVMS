package com.pvms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.pvms.util.DbUtil;
import com.pvms.util.Queries;
import com.pvms.bean.Transaction;

public class PaymentDao {
	
	
	public boolean doPay(Transaction tdn, String uname) throws Exception
	{
		Connection con = DbUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(Queries.createTransaction);
		ps.setString(1, tdn.getId());
		ps.setDouble(2, tdn.getAmount());
		ps.setString(3,uname);
		ps.setString(4,tdn.getType());
		int n=ps.executeUpdate();
		con.close();
		if(n>0)
			return true;
		else
			return false;
	}

}
