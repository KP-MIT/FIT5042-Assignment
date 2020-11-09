package fit5042.Assignment.applications;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.mbeans.CustomerManagedBean;

@Named(value = "CustomerApplication")
@ApplicationScoped
public class CustomerManagerApplication {
	
	@ManagedProperty(value = "#{CustomerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private ArrayList<Customer> customers;
	
	private boolean showForm = true;
	
	public boolean isShowForm() {
		
		return showForm;
	}
	
	public CustomerManagerApplication() throws Exception {
		customers = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "customerManagedBean");
	}

}
