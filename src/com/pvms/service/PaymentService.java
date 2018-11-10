package com.pvms.service;

import com.pvms.dao.PaymentDao;
import com.pvms.bean.Transaction;

public class PaymentService {
	
	public boolean doPay(Transaction tdn, String uname) throws Exception
	{
		PaymentDao pdao=new PaymentDao();
		boolean b=pdao.doPay(tdn,uname);
		return b;
	}

}
