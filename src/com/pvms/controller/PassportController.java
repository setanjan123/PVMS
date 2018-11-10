package com.pvms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pvms.bean.City;
import com.pvms.bean.Passport;
import com.pvms.bean.PSK;
import com.pvms.bean.State;
import com.pvms.bean.Transaction;
import com.pvms.service.PassportService;

/**
 * Servlet implementation class PassportController
 */
public class PassportController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		HttpSession sn = request.getSession();
		String opt = null;
		if(request.getParameter("opt")==null)
		opt=(String) request.getAttribute("opt");
		else
		opt=request.getParameter("opt");
		if(opt.equals("opt1")||opt.equals("opt6"))
		{
			
			ArrayList<City>cities=new ArrayList<City>();
			ArrayList<State>states=new ArrayList<State>();
			ArrayList<PSK>psk= new ArrayList<PSK>();
			PassportService ps=new PassportService();
			String uname = (String) sn.getAttribute("uname");
			boolean b=false;
			try {
			   b=ps.getPassId(uname);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PassportService.getDetails(cities, states, psk);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sn.setAttribute("States", states);
			sn.setAttribute("Cities", cities);
			sn.setAttribute("PSK", psk);
			RequestDispatcher rd =null;
			if(opt.equals("opt1")){
				if(!b){
					rd = request.getRequestDispatcher("jsp/apply_passport.jsp");
					rd.forward(request, response);
				}
				else
				{
					String msg="You already have a passport. Please Select <a href=\"PassportController?opt=opt6\">Renew Passport</a> if you want to renew it.";
					request.setAttribute("message",msg);
					rd = request.getRequestDispatcher("jsp/message.jsp");
					rd.forward(request, response);
				}
			}
			   
			else if(opt.equals("opt6"))
			{
				if(b){
					rd = request.getRequestDispatcher("jsp/passport_renewal.jsp");
					rd.forward(request, response);
				}
				else
				{
					String msg="You do not have a passport. Please Apply for one";
					request.setAttribute("message",msg);
					rd = request.getRequestDispatcher("jsp/message.jsp");
					rd.forward(request, response);
				}
			}
			
		}
		else if(opt.equals("opt2"))
		{
			
			String stateid = request.getParameter("stateid");
			@SuppressWarnings("unchecked")
			ArrayList<City>cities= (ArrayList<City>) sn.getAttribute("Cities");
			for(City c: cities)
			{
			   if(c.getState_id().equals(stateid))
				pw.println("<option value="+c.getId()+">"+c.getName()+"</option>");
			}
		}
		else if(opt.equals("opt4"))
		{
			
			String cityid = request.getParameter("cityid");
			@SuppressWarnings("unchecked")
			ArrayList<PSK>psk= (ArrayList<PSK>) sn.getAttribute("PSK");
			for(PSK p: psk)
			{
			   if(p.getCity_id().equals(cityid))
				pw.println("<option value="+p.getId()+">"+p.getName()+"</option>");
			}
		}
		else if(opt.equals("opt5"))
		{
			String uname = (String) sn.getAttribute("uname");
			String state = request.getParameter("States");
			String city = request.getParameter("Cities");
			String psk = request.getParameter("PSK");
			String pin = request.getParameter("PIN");
			String booklettype = request.getParameter("booklettype");
			String servicetype = request.getParameter("servicetype");
			Passport pd = new Passport();
		    pd.setUsername(uname);
		    pd.setState(state);
		    pd.setCity(city);
		    pd.setPsk(psk);
		    pd.setPin(Long.parseLong(pin));
		    pd.setBooklettype(booklettype);
		    pd.setServicetype(servicetype);
		    sn.setAttribute("passport_details",pd);
		    PassportService ps = new PassportService();
		    try {
				Transaction t = ps.getApplyCost(pd);
				if(t==null)
				{
					String msg="Sorry Transaction Failed";
					request.setAttribute("message",msg);
					RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
					rd.forward(request, response);
				}
				else
				{	
				sn.setAttribute("txn",t);
				RequestDispatcher rd=request.getRequestDispatcher("jsp/payment.jsp");
				rd.forward(request,response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
		else if (opt.equals("opt3"))
		{
			PassportService ps=new PassportService();
			Passport p=(Passport) sn.getAttribute("passport_details");
			Transaction t=(Transaction) sn.getAttribute("txn");
			try {
				String pass_Id=ps.submitPassDetails(p, t);
				String msg=null;
				if(pass_Id==null)
					msg="Sorry! Transaction failed";
				else
					msg="Transaction successful. Your Passport Id is "+pass_Id;
				request.setAttribute("message",msg);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(opt.equals("opt8"))
		{
			String uname = (String) sn.getAttribute("uname");
			String state = request.getParameter("States");
			String city = request.getParameter("Cities");
			String psk = request.getParameter("PSK");
			String pin = request.getParameter("PIN");
			String booklettype = request.getParameter("booklettype");
			String servicetype = request.getParameter("servicetype");
			Passport pd = new Passport();
		    pd.setUsername(uname);
		    pd.setState(state);
		    pd.setCity(city);
		    pd.setPsk(psk);
		    pd.setPin(Long.parseLong(pin));
		    pd.setBooklettype(booklettype);
		    pd.setServicetype(servicetype);
		    sn.setAttribute("passport_details",pd);
		    PassportService ps = new PassportService();
		    try {
				Transaction t = ps.getRenewalCost(pd);
				if(t==null)
				{
					String msg="Can't renew Passport";
					request.setAttribute("message",msg);
					RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
					rd.forward(request, response);
				}
				else
				{	
				sn.setAttribute("txn",t);
				RequestDispatcher rd=request.getRequestDispatcher("jsp/payment.jsp");
				rd.forward(request,response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(opt.equals("prensucc"))
		{
			PassportService ps=new PassportService();
			Passport p=(Passport) sn.getAttribute("passport_details");
			Transaction t=(Transaction) sn.getAttribute("txn");
			try {
				boolean b=ps.RenewPass(p, t);
				String msg=null;
				if(!b)
					msg="Sorry! Transaction failed";
				else
					msg="Passport renewal successful";
				request.setAttribute("message",msg);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
