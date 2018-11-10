package com.pvms.service;
import com.pvms.bean.ForgotPassDetail;
import com.pvms.bean.LoginDetail;
import com.pvms.dao.LoginDao;

public class LoginService {
	
	public boolean validateUser(LoginDetail l) throws Exception
	{
		LoginDao ld=new LoginDao();
		boolean userExist=ld.validateUser(l);
		return userExist;
	}
	
	public String validateHques(String hans,ForgotPassDetail fpd)
	   {
		   if(hans.equals(fpd.getHans()))
		   return fpd.getPass();
		   else
		   return null;
	   }
	
	public ForgotPassDetail getHques(String uname1) throws Exception
	{
		LoginDao ld=new LoginDao();
		ForgotPassDetail fpd=ld.getDetail(uname1);
		return fpd;
	}
}
