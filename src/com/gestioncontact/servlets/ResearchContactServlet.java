package com.gestioncontact.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gestioncontact.entities.Contact;
import com.gestioncontact.services.ContactService;

/**
 * Servlet implementation class ResearchContactServlet
 */
public class ResearchContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearchContactServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		ContactService cs = (ContactService)ac.getBean("ContactService");
		List<Contact> contacts = cs.searchByMail((String)request.getParameter("email"));

		
		
		RequestDispatcher rd = request.getRequestDispatcher( "ContactSearchResults.jsp" );
		
		request.setAttribute("contacts", contacts);
		
		System.out.println("YOOHOO "+request.getAttribute("contacts"));
		
		rd.forward(request, response);
	}

}
