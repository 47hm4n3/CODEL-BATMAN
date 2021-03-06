package com.gestioncontact.services;

import java.util.List;

import com.gestioncontact.dao.ContactDAO;
import com.gestioncontact.entities.Contact;
import com.gestioncontact.entities.User;


public class ContactService {
	protected ContactDAO dao;
	
	public ContactService(){

	}

	public ContactService(ContactDAO dao){
		this.dao = dao;
	}
	
	public Contact create(Contact c ){
		return dao.create(c);
	}
	
	public Contact create(String firstName, String lastName, String nickName, String email, User user) {
		return dao.create(firstName, lastName, nickName, email, user);
	}
	
	public Contact getById(long id){
		return dao.getById(id);
	}
	
	public List<Contact> getByPart(String str, Long user_id){
		return dao.searchByPart(str, user_id);
	}
	
	public Contact getByMail(String mail){
		return dao.getByMail(mail);
	}
	
	public List<Contact> searchByMail(String mail){
		return dao.searchByMail(mail);
	}

	public Contact getContact(long id) {
		return dao.getById(id);
	}

	public List<Contact> getAll(long id) {
		return dao.getAll(id);
	}

	public List<Contact> getContacts(long id) {
		return dao.getContacts(id); 
	}

	public void deleteById(long id) {
		dao.deleteById(id);
	}

	public void delete(Contact c) {
		dao.delete(c);
	}
	public void update(Contact c){
		dao.updateContact(c);
	}
	
	public ContactDAO getDao() {
		return dao;
	}

	public void setDao(ContactDAO dao) {
		this.dao = dao;
	}
}
