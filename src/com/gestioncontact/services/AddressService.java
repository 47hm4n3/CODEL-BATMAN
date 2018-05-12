package com.gestioncontact.services;



import java.util.List;

import com.gestioncontact.dao.AddressDAO;
import com.gestioncontact.entities.Address;

public class AddressService {
	private AddressDAO dao;
	
	public AddressService(){

	}
	public AddressService(AddressDAO dao){
		this.dao = dao;
	}
	
	public AddressDAO getDao() {
		return dao;
	}

	public void setDao(AddressDAO dao) {
		this.dao = dao;
	}
	
	public Address create(String placeId,String lat,String lng, String street, String city, String zip, String country, long streetNb) {
		return dao.create(placeId, lat, lng, street, city, zip, country, streetNb);
	}
	
	public Address create(Address a){
		return dao.create(a);
	}
	
	public List<Address> getByPart(String str){
		return dao.searchByPart(str);
	}
}
