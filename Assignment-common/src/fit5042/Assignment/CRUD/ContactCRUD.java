package fit5042.Assignment.CRUD;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import fit5042.Assignment.entities.Contact;

@Remote
public interface ContactCRUD {

	public void addContact(Contact contact) throws Exception;
	
	public Contact searchContactById(int contactId) throws Exception;
	
	public List<Contact> searchContactByCustomerId(int customerId);
	
	public List<Contact> getAllContacts() throws Exception;

	public void editContact(Contact contact) throws Exception;
	
	public void removeContact(int contactId) throws Exception;

	
}
