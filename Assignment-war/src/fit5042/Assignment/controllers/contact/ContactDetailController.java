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
import fit5042.Assignment.mbeans.ContactManagedBean;

@Named("contactDetailController")
@RequestScoped
public class ContactDetailController implements Serializable{

	@ManagedProperty(value = "#{ContactManagedBean}")
	ContactManagedBean contactManagedBean;
	
	private boolean showForm = true;
	
	public boolean isShowForm() {
		
		return showForm;
	}
	
	private List<Contact> contacts;
	
	private int customerId;
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public ContactDetailController() {
		contacts = new ArrayList<>();
		
		customerId = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap()
				.get("customerID"));
		
		setCustomerId(customerId);
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		contactManagedBean = (ContactManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "contactManagedBean");
		
		searchContactByCustomerId(customerId);
		
	}
	

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void searchContactByCustomerId(int customerId){
		contacts.clear();
		for(fit5042.Assignment.entities.Contact contact : contactManagedBean.searchContactByCustomerId(customerId))
		{
			contacts.add(contact);
			setContacts(contacts);
		}
		showForm=true;
	}
	
}
