package com.gestioncontact.services;

import java.util.List;

import com.gestioncontact.dao.PhoneNumberDAO;
import com.gestioncontact.entities.PhoneNumber;

public class PhoneNumberService {
	private PhoneNumberDAO dao;
	
	public PhoneNumberService(){

	}

	public PhoneNumberService(PhoneNumberDAO dao){
		this.dao = dao;
	}
	
	public PhoneNumber create(String kind, String number) {
		return dao.create(kind, number);
	}
	
	public PhoneNumber create(PhoneNumber pn){
		return dao.create(pn);
	}

	public PhoneNumberDAO getDao() {
		return dao;
	}

	public void setDao(PhoneNumberDAO dao) {
		this.dao = dao;
	}
	
	public List<PhoneNumber> getByPart(String str, Long user_id){
		return dao.searchByPart(str, user_id);
	}
}
