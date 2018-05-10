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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gestioncontact.dao.ContactGroupDAO;
import com.gestioncontact.dao.UserDAO;
import com.gestioncontact.entities.Address;
import com.gestioncontact.entities.Contact;
import com.gestioncontact.entities.ContactGroup;
import com.gestioncontact.entities.Enterprise;
import com.gestioncontact.entities.PhoneNumber;
import com.gestioncontact.services.AddressService;
import com.gestioncontact.services.ContactGroupService;
import com.gestioncontact.services.ContactService;
import com.gestioncontact.services.EnterpriseService;
import com.gestioncontact.services.PhoneNumberService;

/**
 * Servlet implementation class AddContactServlet
 */	
@Transactional
public class AddContactGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Autowired
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupName = request.getParameter("group_name");
		
		boolean success = true;
		Exception catchedException = null;
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			
			//int nbPhones = Integer.parseInt(request.getParameter("nb_phones"));
			//System.out.println("NBPhones "+nbPhones);

				ContactGroupService service = null;
				ContactGroup c = null;
				service = (ContactGroupService) ac.getBean("ContactGroupService");
				service.setDao((ContactGroupDAO) ac.getBean("ContactGroupDAO"));
				c = (ContactGroup)ac.getBean("ContactGroup");
				c.setGroupName(groupName);
				c.setUser(((UserDAO)ac.getBean("UserDAO")).getById(Long.parseLong(request.getSession().getAttribute("logged_user").toString())));

				/*if(nbPhones >= 0){
					PhoneNumberService phoneService = (PhoneNumberService) ac.getBean("PhoneNumberService");

					for (int i = 0; i <= nbPhones; i++){
						String kind = request.getParameter("phones["+i+"].phoneKind");
						String number = request.getParameter("phones["+i+"].phoneNumber");
						
						if(kind != null && number != null){						
							PhoneNumber pn = (PhoneNumber) ac.getBean("PhoneNumber");
							//c.addProfile(pn);
						}
					}
				}*/
				service.create(c);		
		
		PrintWriter writer = response.getWriter();
		
		if(success){
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
