package fit5042.Assignment.mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fit5042.Assignment.CRUD.CustomerCRUD;
import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.entities.RelationManager;

@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable{
	
	@EJB
	CustomerCRUD customerCRUD;
	
	public CustomerManagedBean() {
		
	}
	
	public void addCustomer(Customer newCustomer) {
		//Customer customer = convertCustomerToEntity(newCustomer);
		try {
			customerCRUD.addCustomer(newCustomer);
			
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
//	private Customer convertCustomerToEntity(fit5042.Assignment.controllers.customer.Customer newCustomer) {
//		// TODO Auto-generated method stub
//		Customer customer = new Customer();
//		customer.setCustomerId(newCustomer.getCustomerId());
//		customer.setCustomerName(newCustomer.getCustomerName());
//		customer.setAddress(newCustomer.getAddress());
//		customer.setCountry(newCustomer.getCountry());
//		customer.setIndustry(newCustomer.getIndustry());
//		customer.setJoinDate(newCustomer.getJoinDate());
//		customer.setUnitPurchased(newCustomer.getUnitPurchased());
//		customer.setTags(newCustomer.getTags());
//		
//		int managerId = newCustomer.getManagerId();
//		String name = newCustomer.getManagerName();
//		String dept = newCustomer.getDept();
//		RelationManager relationManager = new RelationManager(managerId,name,dept);
//		if(relationManager.getManagerId()==0) {
//			relationManager=null;
//		}
//		customer.setRelationManager(relationManager);
//		return customer;
//	}

	public Customer searchCustomerById(int customerId) {
		try {
			return customerCRUD.searchCustomerById(customerId);
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<Customer> searchCustomerByUnitPurchased(double unitPurchased){
		try {
			return customerCRUD.SearchCustomerByUnitPurchased(unitPurchased);
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<Customer> getAllCustomers(){
		try {
			List<Customer> customers = customerCRUD.getAllCustomer();
			return customers;
			
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return null;
	}

	public void editCustomer(Customer customer) {
		try {
			customerCRUD.editCustomer(customer);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer updated succesfully!"));
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void removeCustomer(int customerId) {
		try {
			customerCRUD.removeCustomer(customerId);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer deleted succesfully!"));
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
