package com.gestioncontact.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gestioncontact.entities.Address;
import com.gestioncontact.entities.Contact;
import com.gestioncontact.entities.ContactGroup;
import com.gestioncontact.entities.PhoneNumber;
import com.gestioncontact.services.AddressService;
import com.gestioncontact.services.ContactGroupService;
import com.gestioncontact.services.ContactService;
import com.gestioncontact.services.PhoneNumberService;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Servlet implementation class ResearchContactServlet
 */
public class ResearchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearchAllServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		PrintWriter out = response.getWriter();
		ContactService cs = (ContactService)ac.getBean("ContactService");
		ContactGroupService cgs = (ContactGroupService)ac.getBean("ContactGroupService");
		PhoneNumberService pns = (PhoneNumberService)ac.getBean("PhoneNumberService");
		AddressService as = (AddressService)ac.getBean("AddressService");
		
		Long user_id = (Long) request.getSession().getAttribute("logged_user");
		
		List<Contact> resultsC = cs.getByPart((String)request.getParameter("param"), user_id);
		//List<ContactGroup> resultsCG = cgs.getByPart((String)request.getParameter("param"), user_id);
		//List<PhoneNumber> resultsPN = pns.getByPart((String)request.getParameter("param"), user_id);
		List<Address> resultsA = as.getByPart((String)request.getParameter("param"));
		
		/*List<Object> results = new ArrayList<Object>();
				results.addAll(resultsC);
				results.addAll(resultsCG);
				results.addAll(resultsPN);
		/*for(Object r:results){
			out.println("<div>"+r.toString()+"</div>");
		}*/
				
		StringWriter sw = new StringWriter();
		JsonFactory factory = new JacksonFactory();
		JsonGenerator jsonGen = factory.createJsonGenerator(sw);
		jsonGen.writeStartObject();
		jsonGen.writeFieldName("contacts");
		jsonGen.writeStartArray();
		for(int i = 0; i < resultsC.size(); i ++){
			Contact c = resultsC.get(i);
			jsonGen.writeStartObject();
		
			jsonGen.writeFieldName("id");
			jsonGen.writeString(String.valueOf(c.getId()));
			
			jsonGen.writeFieldName("first_name");
			jsonGen.writeString(c.getFirstName());
			
			jsonGen.writeFieldName("last_name");
			jsonGen.writeString(c.getLastName());
		
			jsonGen.writeFieldName("email");
			jsonGen.writeString(c.getEmail());
			
			jsonGen.writeEndObject();
		}
		jsonGen.writeEndArray();
		
		jsonGen.writeFieldName("contactGroups");
		jsonGen.writeStartArray();
		jsonGen.writeEndArray();
		
		jsonGen.writeFieldName("phoneNumbers");
		jsonGen.writeStartArray();
		jsonGen.writeEndArray();
		
		jsonGen.writeEndObject();
		jsonGen.close();
		
		String doc = sw.toString();
		System.out.println(doc);
		out.println(doc);
		//RequestDispatcher rd = request.getRequestDispatcher( "AllSearchResults.jsp" );
		
		//request.setAttribute("results", results);		
		//rd.forward(request, response);
	}

}
