package com.gestioncontact.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.gestioncontact.entities.Contact;
import com.gestioncontact.entities.User;

@Transactional
public class ContactDAO extends HibernateDaoSupport {

	public Contact create (Contact c){
		getHibernateTemplate().persist(c);
		getHibernateTemplate().save(c);
		return c;
	}
	
	public Contact create(String firstName, String lastName, String nickName, String email, User user) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		Contact c = (Contact) ac.getBean("Contact");
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setNickName(nickName);
		c.setEmail(email);
		c.setUser(user);

		getHibernateTemplate().persist(c);
		getHibernateTemplate().save(c);
		return c;
	}

	public Contact getById(long id) {

		return (Contact)getHibernateTemplate().get(Contact.class, id);
	}

	public Contact getByMail(String mail) {
		//Written in HQL
		String query = "from Contact as t where t.email = :mail";

		return (Contact) getHibernateTemplate().find(query, mail);
	}

	public List<Contact> searchByMail(String mail) {
		//Written in HQL
		String query = "from Contact as t where t.email like :mail";		
		String str ="%" + mail + "%";
	
		List<Contact> rs = ((List<Contact>) getHibernateTemplate().find(query, mail));
		return rs;
	}	
	
	public void delete(Contact c){
		getHibernateTemplate().delete(c);
	}

	public List<Contact> getAll(long userId) {
		
		String query = "from Contact as t where t.user.id  = ?";		
		List<Contact> rs = ((List<Contact>) getHibernateTemplate().find(query, userId));
		return rs;
	}
	
	public List<Contact> getContacts(long userId){

		String query = "from Contact as t where t.user.id  = ? and t.class = Contact";
		List<Contact> rs = (List<Contact>) getHibernateTemplate().find(query, userId);
		return (List<Contact>) rs;
	}

	public void deleteById(long id) {
		// TODO Auto-generated method stub
		
	}

	public List<Contact> searchByPart(String str, Long user_id) {
		//Written in HQL
		String query = "from Contact as t where t.firstName like ? or t.lastName like ? or t.nickName like ? or  t.email like ? and t.user.id = ?";		
		String param ="%"+str+"%";
	
		List<Contact> rs = ((List<Contact>) getHibernateTemplate().find(query, "%"+param+"%", "%"+param+"%", "%"+param+"%", "%"+param+"%", user_id));
		return rs;
	}
	
	public void updateContact (Contact c){
		getHibernateTemplate().merge(c); 
		//getHibernateTemplate().update(c);
	}
}
