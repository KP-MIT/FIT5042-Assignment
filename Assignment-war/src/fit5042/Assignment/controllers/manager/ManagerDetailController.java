package fit5042.Assignment.controllers.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.entities.RelationManager;
import fit5042.Assignment.mbeans.CustomerManagedBean;
import fit5042.Assignment.mbeans.ManagerManagedBean;

@Named("managerDetailController")
@RequestScoped
public class ManagerDetailController implements Serializable{

	@ManagedProperty("ManagerManagedBean")
	ManagerManagedBean managerManagedBean;
	
	@ManagedProperty("CustomerManagedBean")
	CustomerManagedBean customerManagedBean;
	
	private int managerId=0;
	
	private RelationManager manager;

	ManagerViewController view;
	
	private List<RelationManager> rManagers = new ArrayList<>();
	
	private List<Customer> customers = new ArrayList<>();
	
	private Set<Customer> customer;

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public RelationManager getManager() {
		return manager;
	}

	public void setManager(RelationManager manager) {
		this.manager = manager;
	}

	public ManagerDetailController() {
		
		customer = new HashSet<>();
		
		managerId = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.get("managerID"));
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		view = (ManagerViewController) FacesContext.getCurrentInstance()
									.getApplication().getELResolver()
									.getValue(context, null, "managerViewController");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		managerManagedBean = (ManagerManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "managerManagedBean");
		
		ELContext elCon = FacesContext.getCurrentInstance().getELContext();
		
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elCon, null, "customerManagedBean");
		
		
		manager = getManagerFromController();
		
		customer = getCustomer(managerId);
	}

	private fit5042.Assignment.entities.RelationManager getManagerFromController() {
		// TODO Auto-generated method stub
		if (manager == null) {
			int searchKey = managerId-1; 
			return view.getManagers().get(searchKey);
		}
		return manager;
	
	
	}

	public Set<Customer> getCustomer(int managerId) {
		customer.clear();
		customers.clear();
		customers = getCustomers();
		managerId = getManagerId();
		for(Customer c : customers) {
			if (c.getRelationManager().getManagerId() == managerId) {
				customer.add(c);
			}
		}
		setCustomer(customer);
		return customer;
	}
	
	public List<String> getCustomerNames(){
		List<String> customerNames = new ArrayList<>();
		managerId = getManagerId();
		customer = getCustomer(managerId);
		for (Customer c : customer) {
			customerNames.add(c.getCustomerName());
		}
		return customerNames;
	}

	private List<RelationManager> getManagers() {
		// TODO Auto-generated method stub
		rManagers.clear();
		rManagers = managerManagedBean.getAllManagers();
		return rManagers;
	}

	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		customers = customerManagedBean.getAllCustomers();
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
