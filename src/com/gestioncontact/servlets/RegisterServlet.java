package com.gestioncontact.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gestioncontact.entities.Contact;
import com.gestioncontact.entities.ContactGroup;
import com.gestioncontact.entities.Enterprise;
import com.gestioncontact.entities.User;
import com.gestioncontact.services.ContactGroupService;
import com.gestioncontact.services.ContactService;
import com.gestioncontact.services.EnterpriseService;
import com.gestioncontact.services.UserService;
/**
 * Servlet implementation class LoginServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean redirect = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.redirect = true;
		this.doPost(request, response);
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		boolean success = false;
		Exception catchedException = null;
		String firstName = request.getParameter( "first_name" );
		String lastName = request.getParameter( "last_name" );
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		String confirm = request.getParameter( "confirm" );
		
		if (firstName != "" && lastName != "" && email != ""){
		
			if( password != null && password.compareTo(confirm) == 0){
				User u = (User) ac.getBean("User");
				UserService uservice = (UserService) ac.getBean("UserService");
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setEmail(email);
				u.setPassword(password);
				u=uservice.create(u);
				
				success =true;
				
				ContactGroupService cgservice = (ContactGroupService) ac.getBean("ContactGroupService");
				ContactGroup cgWork = (ContactGroup) ac.getBean("ContactGroup");
				cgWork.setGroupName("Work");
				cgWork.setUser(u);
				ContactGroup cgFamily = (ContactGroup) ac.getBean("ContactGroup");
				cgFamily.setGroupName("Family");
				cgFamily.setUser(u);
				ContactGroup cgFriends = (ContactGroup) ac.getBean("ContactGroup");
				cgFriends.setGroupName("Friends");
				cgFriends.setUser(u);
				
				cgservice.create(cgWork);
				cgservice.create(cgFamily);
				cgservice.create(cgFriends);
				
				request.getSession().setAttribute("logged_user", u.getId());
			}
		}else{
			System.out.println("BAD ! => rejet");
		}
		
		PrintWriter writer = response.getWriter();
		
		if(success){
			System.out.println("DANS LE IF SUCCESS");
			ContactService cs = (ContactService) ac.getBean("ContactService");
			List<Contact> contacts = cs.getContacts((Long) request.getSession().getAttribute("logged_user"));
			
			EnterpriseService es = (EnterpriseService) ac.getBean("EnterpriseService");
			List<Enterprise> enterprises = es.getEnterprises((Long) request.getSession().getAttribute("logged_user"));
			
			RequestDispatcher rd = request.getRequestDispatcher( "Main.jsp" );
			
			request.setAttribute("contacts", contacts);
			request.setAttribute("enterprises", enterprises);
			
			rd.forward(request, response);
		}else{
			Exception catchedException2 = catchedException;
			writer.println("Contact NON ajouté."+catchedException2.getLocalizedMessage());
		}
		
	}
}
