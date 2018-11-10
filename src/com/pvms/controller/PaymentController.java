package com.pvms.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pvms.bean.Transaction;
import com.pvms.service.PaymentService;

/**
 * Servlet implementation class PaymentController
 */
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sn=request.getSession();
		String isValid=request.getParameter("isValid");
				if(isValid==null)
				{
					RequestDispatcher rd=request.getRequestDispatcher("jsp/payment.jsp");
					rd.forward(request,response);
					
				}
				else
				{
					Transaction t=(Transaction) sn.getAttribute("txn");
					PaymentService ps=new PaymentService();
					String uname=(String) sn.getAttribute("uname");
					try {
						boolean b=ps.doPay(t,uname);
						if(b)
						{
							
							RequestDispatcher rd=null;
							if(t.getType().equals("Apply Visa"))
							{
								request.setAttribute("opt","opt3");
								rd=request.getRequestDispatcher("VisaController");
							}
							else if(t.getType().equals("Cancel Visa"))
								{
								   request.setAttribute("opt","opt7");
								   rd = request.getRequestDispatcher("VisaController");
								}
							else if(t.getType().equals("Apply Passport"))
							{
								request.setAttribute("opt","opt3");
								rd = request.getRequestDispatcher("PassportController");
							}
							else
							{
								request.setAttribute("opt","prensucc");
								rd = request.getRequestDispatcher("PassportController");
							}
							
							rd.forward(request,response);
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
