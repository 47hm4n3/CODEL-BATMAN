package com.gestioncontact.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.gestioncontact.entities.Contact;

public class Helper {
	static List<Contact> contacts = new ArrayList<Contact>();
	
	public static List<Contact> getPersistanceLayer(){
		return Helper.contacts;
	}
	
	public static void displayHtmlHead(PrintWriter writer){
		writer.println("<html>");
		writer.println("<body>");
		writer.println("<head>");
		writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/bootstrap.css\">");
		writer.println("</head>");
	}
	
	public static void displayHtmlFoot(PrintWriter writer){
		writer.println("</body>");
		writer.println("</html>");
	}
}
