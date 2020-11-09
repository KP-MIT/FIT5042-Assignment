package fit5042.Assignment.controllers.customer;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.controllers.contact.ContactDetailController;
import fit5042.Assignment.entities.Contact;
import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.mbeans.ContactManagedBean;
import fit5042.Assignment.mbeans.CustomerManagedBean;


@Named("customerViewController")
@RequestScoped
public class CustomerViewController{

	@ManagedProperty(value = "#{CustomerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private ArrayList<Customer> customers;
	
	private int searchId;
	
	private double unitPurchased;
    
    private boolean showForm = true;
	
	public boolean isShowForm() {
		
		return showForm;
	}

    public CustomerViewController() {

    	customers = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "customerManagedBean");
		
		updateCustomerList();
    }

    private void updateCustomerList() {
		// TODO Auto-generated method stub
		if (customers != null && customers.size()>0 ) {
			
		}
		else {
			customers.clear();
			
			for (fit5042.Assignment.entities.Customer customer : customerManagedBean.getAllCustomers())
			{
				customers.add(customer);
			}
			setCustomers(customers);
		}
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
    
	public void searchAll() {
		customers.clear();
		for (fit5042.Assignment.entities.Customer customer: customerManagedBean.getAllCustomers()) {
			customers.add(customer);
			setCustomers(customers);
		}
	}
	
	public void searchCustomerById(int customerId) {
		customers.clear();
		customers.add(customerManagedBean.searchCustomerById(customerId));
	}
	
	public void searchCustomerByUnitPurchased(double unitPurchased) {
		customers.clear();
		for (Customer customer : customerManagedBean.searchCustomerByUnitPurchased(unitPurchased))
		{
			customers.add(customer);
		}
		setCustomers(customers);
	}
	
	public void removeCustomer(int customerId) {
        try {
            //remove this property from db via EJB
            customerManagedBean.removeCustomer(customerId);
            //refresh the list in PropertyApplication bean
             searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been deleted succesfully"));
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

	public double getUnitPurchased() {
		return unitPurchased;
	}

	public void setUnitPurchased(double unitPurchased) {
		this.unitPurchased = unitPurchased;
	}
	
	
}
