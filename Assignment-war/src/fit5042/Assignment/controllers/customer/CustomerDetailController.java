package fit5042.Assignment.controllers.customer;

import java.io.Serializable;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Customer;

@Named("customerDetailController")
@RequestScoped
public class CustomerDetailController implements Serializable{

	private int customerId=0;
	
	private Customer customer;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public CustomerDetailController() {
		customerId = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("customerID"));
		
		//customer = new Customer();
		--customerId;
		customer = getCustomerFromController();
	}

	private fit5042.Assignment.entities.Customer getCustomerFromController() {
		// TODO Auto-generated method stub
		if (customer == null) {
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			
			CustomerViewController view = (CustomerViewController) FacesContext.getCurrentInstance()
										.getApplication().getELResolver()
										.getValue(context, null, "customerViewController");
			
			
			Customer customer =  view.getCustomers().get(0);
			return view.getCustomers().get(customerId);
		}
		return customer;
	}
}
