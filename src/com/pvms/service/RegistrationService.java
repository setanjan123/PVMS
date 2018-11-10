package com.pvms.service;


import com.pvms.bean.LoginDetail;
import com.pvms.bean.User;
import com.pvms.dao.RegistrationDao;

public class RegistrationService {

	
	public LoginDetail submitUserDetails(User u) throws Exception
	{
		LoginDetail l=null;
		RegistrationDao rg=new RegistrationDao();
		l=rg.submitUserDetails(u);
		return l;
	}
}
