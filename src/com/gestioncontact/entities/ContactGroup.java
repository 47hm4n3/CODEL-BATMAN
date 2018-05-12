package com.gestioncontact.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ContactGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	private int version;
	String groupName;
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable (joinColumns = {@JoinColumn},
		       inverseJoinColumns = {@JoinColumn})
	private Set<Contact> contacts = new HashSet<Contact>();
	
	private User user;
	
	public ContactGroup() {
		
	}
	
	public ContactGroup(String groupName) {
		this.groupName = groupName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Set<Contact> getContacts(){
		return this.contacts;
	}
	
	public void setContacts(Set<Contact> contacts){
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		if(!contact.getBooks().contains(this))
			contact.addBook(this);
		this.contacts.add(contact);
	}

	public void removeContact(Contact contact) {
		if(contact.getBooks().contains(this))
			contact.removeBook(this);
		this.contacts.remove(contact);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
