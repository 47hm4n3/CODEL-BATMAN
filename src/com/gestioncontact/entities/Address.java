package com.gestioncontact.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	private int version;
	String street, city, zip, placeId, country;
	String lat;
	String lng;
	long streetNb;
	
	public Address() {
		
	}
	
	public Address(String placeId,String lat,String lng, String street, String city, String zip, String country, long streetNb) {
		this.placeId = placeId;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
		this.streetNb = streetNb;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPlaceId(){
		return this.placeId;
	}
	
	public void setPlaceId(String placeId){
		this.placeId = placeId;
	} 
	
	@Override
	public String toString()
	{
		return "MemRef :"+super.toString()+" - Address :"+this.street+" - Number :"+this.city;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public void setStreetNb(long streetNb) {
		this.streetNb = streetNb;
	}
	
	public long getStreetNb(){
		return this.streetNb;
	}

}
