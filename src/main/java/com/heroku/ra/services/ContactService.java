/*
 * Created on 2 Dec 2016 ( Time 21:46:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.heroku.ra.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heroku.ra.dto.UserLoginDTO;
import com.heroku.ra.entities.Contact;
import com.heroku.ra.exceptions.ContactNotFoundException;
import com.heroku.ra.exceptions.IncorrectPassword;
import com.heroku.ra.repository.ContactRepository;

@Service
@Transactional(rollbackFor=ContactNotFoundException.class)
public class ContactService 
{
	private static Logger logger = LoggerFactory.getLogger(ContactService.class);
	
	@Autowired
	private ContactRepository contactRepository;
		
	/*
	 * READ methods
	 */
	public Page<Contact> findAll(int page, int count) {
		
		if (logger.isDebugEnabled())
			logger.debug("ContactService -> findAll Contacts");
		
		return contactRepository.findAll(new PageRequest(page, count));
	}

	public Iterable<Contact> findAll() {
		
		if (logger.isDebugEnabled())
			logger.debug("ContactService -> findAll Contacts");
		
		return contactRepository.findAll();
	}

	public Contact findBySfid(String contact){
		if (logger.isDebugEnabled())
			logger.debug("Retrieving Contact with sfid:" + contact);
		
		return contactRepository.findBySfid(contact);
		
	}

	public Contact get(int id) {
		if (logger.isDebugEnabled())
			logger.debug("Retrieving Contact with id:" + id);
		
		Contact elementToGet = contactRepository.findOne(id);
		
		if (elementToGet == null)
			throw new ContactNotFoundException(id);
		
		return elementToGet;
	}

	public Contact create(Contact contact) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		contact.setPassword(encoder.encode(contact.getPassword()));
		
		if (logger.isDebugEnabled())
			logger.debug("ContactService -> create:" + contact);
			
		return contactRepository.save(contact);
	}

	public Contact update(Contact contact) {
		
		if (logger.isDebugEnabled())
			logger.debug("ContactService -> create:" + contact);

		Contact elementToUpdate = contactRepository.findOne(contact.getId());
		
		if (elementToUpdate == null)
			throw new ContactNotFoundException(contact.getName());
		
		elementToUpdate.update(contact);
		contactRepository.save(elementToUpdate);
		
		return elementToUpdate;
	}

	public Contact delete(int id) {
		if (logger.isDebugEnabled())
			logger.debug("ContactService -> delete:" + id);

		Contact elementToDelete = get(id);
		
		if (elementToDelete == null)
			throw new ContactNotFoundException(id);
		
		contactRepository.delete(elementToDelete);

		return elementToDelete;
	}
	
	public Contact updatePassword(String email, String oldpassword, String newpassword){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Contact c = contactRepository.findByEmail(email);
		
		if (c == null)
			throw new UsernameNotFoundException(email);
		
		if (!encoder.matches(oldpassword, c.getPassword()))
			throw new IncorrectPassword();
//		c.setPassword(encoder.encode(newpassword));
		
		return contactRepository.save(c);
	}

	public Contact createUser(String email, String oldpassword, String newpassword){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Contact c = contactRepository.findByEmail(email);
		
		if (c == null)
			throw new UsernameNotFoundException(email);
		
		if (!encoder.matches(oldpassword, c.getPassword()))
			throw new IncorrectPassword();
		c.setPassword(encoder.encode(newpassword));
		
		return contactRepository.save(c);
	}

	public Contact doLogin(UserLoginDTO u) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Contact c = contactRepository.findByEmail(u.getEmail());
		
		if (c == null)
			throw new ContactNotFoundException(u.getEmail());
		
		if (!encoder.matches(u.getPassword(), c.getPassword()))
			throw new IncorrectPassword();
		
		return c;
	}

}

