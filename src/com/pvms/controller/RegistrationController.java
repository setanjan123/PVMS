package com.pvms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pvms.bean.LoginDetail;
import com.pvms.bean.User;
import com.pvms.service.RegistrationService;

/**
 * Servlet implementation class RegistrationController
 */
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String dt=request.getParameter("dob");
		Date d=null;
		try {
			d=sdf.parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String addr=request.getParameter("addr");
		String phno=request.getParameter("tel");
		String email=request.getParameter("email");
		String edu=request.getParameter("edu");
		String gen=request.getParameter("gen");
		String occup=request.getParameter("occup");
		String hques=request.getParameter("hques");
		String hans=request.getParameter("hans");
		User nu=new User(fname,lname,d,addr,phno,email,edu,occup,gen,hques,hans);
		RegistrationService rs=new RegistrationService();
		try {
			LoginDetail details=rs.submitUserDetails(nu);
			pw.println("Registration Successful . Your username is :"+details.getUname()+" and password is "+details.getPass());
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
		
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
