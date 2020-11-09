package fit5042.Assignment.CRUD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fit5042.Assignment.entities.Contact;
import fit5042.Assignment.entities.Customer;

@Stateless
public class ContactCrudImpl implements ContactCRUD{

	@PersistenceContext(unitName = "Assignment-ejbPU")
	private EntityManager entityManager;
	
	@Override
	public void addContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		List<Contact> contacts = entityManager.createNamedQuery(Contact.GET_ALL_QUERY_NAME).getResultList();
		contact.setContactId(contacts.get(0).getContactId()+1);
		entityManager.persist(contact);
	}

	@Override
	public Contact searchContactById(int contactId) throws Exception {
		// TODO Auto-generated method stub
		Contact contact = entityManager.find(Contact.class, contactId);
		return contact;
	}

	@Override
	public List<Contact> getAllContacts() throws Exception {
		// TODO Auto-generated method stub
		List<Contact> contactList = entityManager.createNamedQuery(Contact.GET_ALL_QUERY_NAME).getResultList();
		return contactList;
	}

	@Override
	public void editContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		try {
			entityManager.merge(contact);
		}catch (Exception ex) {
			
		}
	}

	@Override
	public void removeContact(int contactId) throws Exception {
		// TODO Auto-generated method stub
		Contact contact = entityManager.find(Contact.class, contactId);
		entityManager.remove(contact);
		
	}

	@Override
	public List<Contact> searchContactByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		Customer customer = entityManager.find(Customer.class, customerId);
		Set<Contact> contactSet = new HashSet<>(customer.getContact());
		List<Contact> contacts = new ArrayList<Contact>(contactSet);
		return contacts;
	}

}
