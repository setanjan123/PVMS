package com.pvms.service;

import com.pvms.bean.User;
import com.pvms.dao.HomeDao;

public class HomeService {
	
	public User getDetails(String uname) throws Exception
	{
		HomeDao hdao=new HomeDao();
		return hdao.getDetails(uname);
	}
	
	public void Insert(User u,String uname) throws Exception
	{
		HomeDao hdao=new HomeDao();
		hdao.Update(u, uname);
	}

}
