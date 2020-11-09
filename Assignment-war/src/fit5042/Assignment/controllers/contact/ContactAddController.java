package fit5042.Assignment.controllers.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

@Named("contactAddController")
@RequestScoped
public class ContactAddController implements Serializable {
	
	@ManagedProperty(value = "#{ContactManagedBean}")
	ContactManagedBean contactManagedBean;
	
	@ManagedProperty(value = "#{CustomerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private Contact contact = new Contact();
	
	private List<Customer> customerList = new ArrayList<>();
	
	int customerId=0;
	
	private boolean showForm = true;
	
	ContactViewController view;
	
	private String firstName;
	
	private String lastName;

	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
		
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public void setLastName(String secondName) {
		this.lastName = secondName;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public ContactAddController() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		view = (ContactViewController) FacesContext.getCurrentInstance()
				.getApplication()
				.getELResolver()
				.getValue(context, null, "contactViewController");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		contactManagedBean = (ContactManagedBean) FacesContext.getCurrentInstance()
							.getApplication()
							.getELResolver()
							.getValue(elContext, null, "contactManagedBean");
		
		ELContext con = FacesContext.getCurrentInstance().getELContext();
		
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
							.getApplication().getELResolver()
							.getValue(con, null, "customerManagedBean");
	}
	
	
	public void addContact(Contact newContact) {
		customerId = getCustomerId();
		Customer customer = findCustomerById(customerId);
		newContact.setCustomer(customer);
		firstName = getFirstName();
		lastName = getLastName();
		ContactName contactName = new ContactName(firstName,lastName);
		newContact.setName(contactName);
		try {
			contactManagedBean.addContact(newContact);
			view.searchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been added succesfully"));
		}catch (Exception ex) {
			
		}
		showForm = true;
	}
	
	public Customer findCustomerById(int customerId) {
		customerList.clear();
		customerList = getAllCustomer();
		for (Customer customer : customerList) {
			if (customer.getCustomerId()==customerId) {
				return customer;
			}
		}
		
		return null;
	}
	
	public List<Customer> getAllCustomer() {
		customerList.clear();
		customerList = customerManagedBean.getAllCustomers();
		return customerList;
	}
	
}
