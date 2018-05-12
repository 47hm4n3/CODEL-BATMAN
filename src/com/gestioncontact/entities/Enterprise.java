package com.gestioncontact.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enterprise extends Contact{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	long numSiret;
	
	public Enterprise() {
		
	}
	
	public Enterprise (String nom, String mail, String siret)
	{
		this.numSiret = Integer.parseInt(siret);
		this.firstName = nom;
		this.email = mail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}

}
