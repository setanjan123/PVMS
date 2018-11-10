package com.pvms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pvms.bean.Country;
import com.pvms.bean.Visa;
import com.pvms.bean.Transaction;
import com.pvms.service.VisaService;

/**
 * Servlet implementation class Visa_Controller
 */
public class VisaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisaController() {
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
		HttpSession sn=request.getSession();
		String uname=(String)sn.getAttribute("uname");
		if(uname==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("html/login.html");
			rd.forward(request,response);
		}
		else
		{
			String opt=null;
			if(request.getParameter("opt")==null)
			opt=(String)request.getAttribute("opt");
			else
			opt=request.getParameter("opt");
			if(opt.equals("opt1"))
			{
			List<Country> ct=new ArrayList<Country>();
			VisaService avs=new VisaService();
			try {
				VisaService.getDetails(ct);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String pass_Id=null;
			try {
				pass_Id= avs.getPassId(uname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(pass_Id==null)
			{
				String msg="You don't have passport";
				request.setAttribute("message",msg);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
				rd.forward(request, response);
				
			}
			else
			{
				request.setAttribute("pass_Id",pass_Id);
				request.setAttribute("countries",ct);
				VisaService vs=new VisaService();
				try {
					String occupation=vs.getOccupation(uname);
					request.setAttribute("occupation",occupation);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rd=request.getRequestDispatcher("jsp/apply_visa.jsp");
				rd.forward(request,response);
				
			}
			}
			
			else if(opt.equals("opt2"))
			{
			  String country=request.getParameter("country");
			  String visa_Type=request.getParameter("visa_type");
			  @SuppressWarnings("unchecked")
			  ArrayList<Country> countries=(ArrayList<Country>)sn.getAttribute("countries");
			  Country c1=null;
			  for(Country c:countries)
			  {
				  if(c.getId().equals(country))
				  {
					  c1=c;
					  break;
				  }
			  }
			  Visa nv=new Visa();
				nv.setUsername(uname);
				nv.setCountry(c1);
				nv.setVisaType(visa_Type);
				sn.setAttribute("visa_details",nv);
				VisaService vs=new VisaService();
				try {
					Transaction t=vs.getApplyCost(nv);
					if(t==null)
					{
						String msg="Can't generate Visa. Your passport is about to expire";
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
			else if(opt.equals("opt3"))
			{
				VisaService vs=new VisaService();
				Visa nv=(Visa) sn.getAttribute("visa_details");
				Transaction t=(Transaction) sn.getAttribute("txn");
				try {
					String visa_Id=vs.submitVisaDetails(nv, t);
					String msg=null;
					if(visa_Id==null)
						msg="Sorry! Transaction failed";
					else
						msg="Transaction successful. Your Visa Id is "+visa_Id;
					request.setAttribute("message",msg);
					RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(opt.equals("opt4"))
			{
				VisaService avs=new VisaService();
				List<Visa> visas=new ArrayList<Visa>();
				try {
					avs.getVisas(visas,uname);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(visas.size()==0)
				{
					String msg="You do not have any VISAs";
					request.setAttribute("message",msg);
					RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
					rd.forward(request, response);
				}
				else
				{
					String pass_Id=null;
					try {
						pass_Id= avs.getPassId(uname);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("visas",visas);
					request.setAttribute("passid",pass_Id);
					RequestDispatcher rd=request.getRequestDispatcher("jsp/cancel_visa.jsp");
					rd.forward(request,response);
					
				}
			}
			else if(opt.equals("opt5"))
			{
				String visa_id=request.getParameter("vid");
				VisaService vs=new VisaService();
				Visa v=new Visa();
				v.setUsername(uname);
				v.setVid(visa_id);
				try {
					Transaction t=vs.getCancelCost(v);
					if(t==null)
					{
						String msg="Cannot cancel Visa";
						request.setAttribute("message",msg);
						RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
						rd.forward(request, response);
					}
					else
					{
						sn.setAttribute("vid", visa_id);
						sn.setAttribute("txn",t);
						RequestDispatcher rd=request.getRequestDispatcher("jsp/payment.jsp");
						rd.forward(request,response);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(opt.equals("opt7"))
			{   
				
				String visa_id=(String)sn.getAttribute("vid");
				VisaService vsobj=new VisaService();
				boolean b=vsobj.CancelVisa(visa_id);
				String msg=null;
				if(b)
				{
					boolean b2=vsobj.CancelVisa(visa_id);
					if(b2)
					msg="Visa successfully cancelled";
					else
					msg="Sorry Transaction Failed. Please try again";
				}
				else
				msg="Sorry Transaction Failed. Please try again";
				request.setAttribute("message",msg);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/message.jsp");
				rd.forward(request, response);
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
