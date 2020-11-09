package fit5042.Assignment.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fit5042.Assignment.CRUD.ContactCRUD;
import fit5042.Assignment.CRUD.RelationManagerCRUD;
import fit5042.Assignment.entities.Contact;
import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.entities.RelationManager;

@ManagedBean(name = "contactManagedBean")
@SessionScoped
public class ContactManagedBean implements Serializable{
	
	@EJB
	ContactCRUD contactCRUD;
	
	public ContactManagedBean() {
		
	}
	
	public void addContact(Contact newContact) {
		//Contact contact = convertContactToEntity(newContact);
		try {
			contactCRUD.addContact(newContact);
		}catch (Exception ex) {
			Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
//	private Contact convertContactToEntity(fit5042.Assignment.controllers.contact.Contact newContact) {
//		// TODO Auto-generated method stub
//		Contact contact = new Contact();
//		contact.setContactId(newContact.getContactId());
//		contact.setName(newContact.getName());
//		contact.setEmail(newContact.getEmail());
//		contact.setPhoneNumber(newContact.getPhoneNumber());
//		contact.setAddress(newContact.getAddress());
//		contact.setTags(newContact.getTags());
//		
//		int customerId = newContact.getCustomerId();
//		String customerName = newContact.getCustomerName();
//		String address = newContact.getcAddress();
//		String industry = newContact.getIndustry();
//		String country = newContact.getCountry();
//		String unitPurchased = newContact.getUnitPurchased();
//		String joinDate =  newContact.getJoinDate();
//		
//		int managerId = newContact.getCustomer().getRelationManager().getManagerId();
//		String name = newContact.getCustomer().getRelationManager().getManagerName();
//		String dept = newContact.getCustomer().getRelationManager().getDept();
//		
//		RelationManager relationManager = new RelationManager(managerId,name,dept);
//		Set<Contact> contacts = new HashSet<>();
//		contacts = newContact.getCustomer().getContact();
//		Set<String> tags = new HashSet<>();
//		tags = newContact.getCustomer().getTags();
//		
//		Customer customer = new Customer(customerId,customerName,address,industry,country,unitPurchased,
//				joinDate,relationManager,contacts,tags);
//		if (customer.getCustomerId()==0) {
//			customer = null;
//		}
//		contact.setCustomer(customer);
//		return contact;
//	}

	public Contact searchContactById(int contactId) {
		try {
			return contactCRUD.searchContactById(contactId);
		}catch(Exception ex) {
			Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public List<Contact> searchContactByCustomerId(int customerId){
		try {
			List<Contact> contacts = contactCRUD.searchContactByCustomerId(customerId);
			return contacts;
		}catch (Exception ex) {
			Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public List<Contact> getAllContacts(){
		try {
			List<Contact> contacts = contactCRUD.getAllContacts();
			return contacts;
		}catch (Exception ex) {
			Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void editContact(Contact contact) {
		try {
			contactCRUD.editContact(contact);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact updated succesfully!"));
		}catch(Exception ex) {
			Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void removeContact(int contactId) {
		try {
			contactCRUD.removeContact(contactId);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact deleted succesfully!"));
		}catch(Exception ex) {
			Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
