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

import org.hibernate.validator.internal.util.privilegedactions.SetContextClassLoader;

import fit5042.Assignment.entities.Contact;
import fit5042.Assignment.mbeans.ContactManagedBean;


@Named("contactViewController")
@RequestScoped
public class ContactViewController implements Serializable{

	@ManagedProperty(value = "#{ContactManagedBean}")
	ContactManagedBean contactManagedBean;
	
	private ArrayList<Contact> contacts;
	
	private int searchId;
    
    private boolean showForm = true;
	
	public boolean isShowForm() {
		
		return showForm;
	}

    public ContactViewController() {

    	contacts = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		contactManagedBean = (ContactManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "contactManagedBean");
		
		updateContactList();
    }

    private void updateContactList() {
		// TODO Auto-generated method stub
		if (contacts != null && contacts.size()>0 ) {
			
		}
		else {
			contacts.clear();
			
			for (fit5042.Assignment.entities.Contact contact : contactManagedBean.getAllContacts())
			{
				contacts.add(contact);
			}
			setContacts(contacts);
		}
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
    
	public void searchAll() {
		contacts.clear();
		for (fit5042.Assignment.entities.Contact contact: contactManagedBean.getAllContacts()) {
			contacts.add(contact);
			setContacts(contacts);
		}
	}
	
	public void searchContactById(int contactId) {
		contacts.clear();
		contacts.add(contactManagedBean.searchContactById(contactId));
	}
	
	public void removeContact(int contactId) {
        try {
            //remove this property from db via EJB
            contactManagedBean.removeContact(contactId);
            //refresh the list in PropertyApplication bean
             searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted succesfully"));
        } catch (Exception ex) {

        }
        showForm = true;

    }

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	
	
}
