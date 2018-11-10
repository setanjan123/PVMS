package com.pvms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pvms.bean.User;
import com.pvms.service.HomeService;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt=request.getParameter("opt");
		HttpSession sn=request.getSession();
		String uname=(String)sn.getAttribute("uname");
		if(uname==null)
		{
			response.sendRedirect(request.getContextPath() + "/html/login.html");
		}
		HomeService hs=new HomeService();
		if(opt==null)
		{
			
			try {
				User u=hs.getDetails(uname);
				sn.setAttribute("user",u);
				RequestDispatcher rd=request.getRequestDispatcher("jsp/home.jsp");
				rd.forward(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String fname=request.getParameter("fname");
			String lname=request.getParameter("lname");
			String dt=request.getParameter("dob");
			Date dob=null;
			try {
				dob=sdf.parse(dt);
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
			User u=new User(fname,lname,dob,addr,phno,email,edu,occup,gen,null,null);
			try {
				hs.Insert(u, uname);
				response.setHeader("Refresh","0;URL=HomeController");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
