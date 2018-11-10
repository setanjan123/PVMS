package com.pvms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pvms.bean.ForgotPassDetail;
import com.pvms.service.LoginService;

/**
 * Servlet implementation class PassRecoveryController
 */
public class PassRecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassRecoveryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter pw=response.getWriter();
		 response.setContentType("text/html");
		 LoginService ls=new LoginService();
		 HttpSession sn=request.getSession();
	   String type=request.getParameter("type");
	   if(type.equals("getHQues"))
	   { String uname=request.getParameter("uname");
	   try{
	    ForgotPassDetail fpd = null;
		fpd=ls.getHques(uname);
		if(fpd!=null)
		{
			pw.println("Question : "+fpd.getHques());
			pw.println("<input type=\"text\" id=\"t2\"><br>");
			pw.println("<button id=\"b2\" class=\"btn btn-success\">Submit</button>");
			sn.setAttribute("fpd",fpd);
		}
	  }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   }
	   else if(type.equals("valHans"))
	   {
		   String hans=request.getParameter("hans");
		   ForgotPassDetail fpd=(ForgotPassDetail) sn.getAttribute("fpd");
		   String pass=ls.validateHques(hans,fpd);
		   if(pass==null)
		   pw.println("Wrong Answer");
		   else
		   pw.println("Your Password is : "+pass);
		   
	   }
	   
     pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
