package fit5042.Assignment.controllers.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Contact;
import fit5042.Assignment.entities.ContactName;
import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.mbeans.ContactManagedBean;
import fit5042.Assignment.mbeans.CustomerManagedBean;
import fit5042.Assignment.mbeans.ManagerManagedBean;


@Named("contactEditController")
@RequestScoped
public class ContactEditController implements Serializable{

	@ManagedProperty(value = "#{ContactManagedBean}")
	ContactManagedBean contactManagedBean;
	
	@ManagedProperty(value = "#{CustomerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private int contactId=0;
	
	private int customerId=0;
	
	private String firstName;
	
	private String lastName;
	
	private List<Customer> customerList = new ArrayList<>();
	
	ContactViewController view; 
	
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	private Contact contact;

	public ContactEditController() {
		contactId = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap()
				.get("contactID"));
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		view = (ContactViewController) FacesContext
				.getCurrentInstance()
				.getApplication()
				.getELResolver()
				.getValue(elContext, null, "contactViewController");
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		contactManagedBean = (ContactManagedBean) FacesContext.getCurrentInstance()
								.getApplication().getELResolver()
								.getValue(context, null, "contactManagedBean");
		
		ELContext con = FacesContext.getCurrentInstance().getELContext();
		
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
								.getApplication().getELResolver()
								.getValue(con, null, "customerManagedBean");
		
		contact = getContact();
		firstName = contact.getName().getFirstName();
		lastName = contact.getName().getLastName();
	}
	
	public fit5042.Assignment.entities.Contact getContact() {
		// TODO Auto-generated method stub
		if(contact == null) {
			
			return view.getContacts().get(--contactId);
		}
		return contact;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void editContact(Contact newContact) {
		firstName = getFirstName();
		lastName = getLastName();
		ContactName contactName = new ContactName(firstName,lastName);
		customerId = getCustomerId();
		Customer customer = getCustomerById(customerId);
		newContact.setCustomer(customer);
		newContact.setName(contactName);
		try {
			contactManagedBean.editContact(newContact);
			view.searchAll();
		} catch (Exception ex) {
			
		}
		
	}

	private Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		customerList.clear();
		customerList = getAllCustomers();
		for (Customer customer : customerList) {
			if (customer.getCustomerId() == customerId) {
				return customer;
			}
		}
		return null;
	}

	private List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		customerList.clear();
		customerList = customerManagedBean.getAllCustomers();
		return customerList;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

}
