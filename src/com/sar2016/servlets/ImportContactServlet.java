package com.sar2016.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;

/**
 * Servlet implementation class ImportContactServlet
 */
public class ImportContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> enu = request.getParameterNames();
		int nbContacts = Integer.parseInt(request.getParameter("nb_contacts"));
		System.out.println("Nb_Contacts : "+nbContacts);
		
		ContactService service = new ContactService();
		for(int i = 0; i < nbContacts; i++){
			System.out.println(request.getParameter("contacts["+i+"].email")+" "+request.getParameter("contacts["+i+"].last_name")+" "+request.getParameter("contacts["+i+"].first_name"));
			Contact c = service.create(request.getParameter("contacts["+i+"].first_name"), request.getParameter("contacts["+i+"].last_name"), null, request.getParameter("contacts["+i+"].email"));
		}
		
		
		List<Contact> contacts = service.getContacts();
		
		EnterpriseService es = new EnterpriseService();
		List<Enterprise> enterprises = es.getEnterprises();
		
		RequestDispatcher rd = request.getRequestDispatcher( "Main.jsp" );
		
		request.setAttribute("contacts", contacts);
		request.setAttribute("enterprises", enterprises);
		
		rd.forward(request, response);
	}

}
