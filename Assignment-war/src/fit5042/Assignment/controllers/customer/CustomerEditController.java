package fit5042.Assignment.controllers.customer;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.entities.RelationManager;
import fit5042.Assignment.mbeans.CustomerManagedBean;
import fit5042.Assignment.mbeans.ManagerManagedBean;

@Named("customerEditController")
@RequestScoped
public class CustomerEditController implements Serializable{

	@ManagedProperty(value = "#{ManagerManagedBean}")
	ManagerManagedBean managerManagedBean;
	
	@ManagedProperty(value = "#{CustomerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private int customerId=0;
	
	private int managerId=0;
	
	private List<RelationManager> rManagerList = new ArrayList<>();
	
	private Customer customer;
	
	CustomerViewController view;
	
	private List<String> industries = new ArrayList<String>();
	
	private String industry="";
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<RelationManager> getrManagerList() {
		return rManagerList;
	}

	public void setrManagerList(List<RelationManager> rManagerList) {
		this.rManagerList = rManagerList;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	public List<String> getIndustries() {
		return industries;
	}

	public void setIndustries(List<String> industries) {
		this.industries = industries;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public CustomerEditController() {
		customerId = Integer.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap()
				.get("customerID"));
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		managerManagedBean = (ManagerManagedBean) FacesContext.getCurrentInstance()
								.getApplication().getELResolver()
								.getValue(context, null, "managerManagedBean");
		
		ELContext con = FacesContext.getCurrentInstance().getELContext();
		
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
								.getApplication().getELResolver()
								.getValue(con, null, "customerManagedBean");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		view = (CustomerViewController) FacesContext
				.getCurrentInstance()
				.getApplication()
				.getELResolver()
				.getValue(elContext, null, "customerViewController");
		
		industries = Arrays.asList("Bank","Building","Data Communication","Education"
				,"Farm","Health","Mining","Publishing");
		
		customer = getCustomer();
	}
	
	public fit5042.Assignment.entities.Customer getCustomer() {
		// TODO Auto-generated method stub
		if(customer == null) {
			
			return view.getCustomers().get(--customerId);
		}
		return customer;
	}

	public void editCustomer(Customer newCustomer)
	{
		industry = getIndustry();
		newCustomer.setIndustry(industry);
		managerId = getManagerId();
		RelationManager rManager = getManagerById(managerId);
		newCustomer.setRelationManager(rManager);
		try {
			customerManagedBean.editCustomer(newCustomer);
			view.searchAll();
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been updated succesfully"));
		} catch (Exception ex) {
			
		}
	}

	private RelationManager getManagerById(int managerId) {
		// TODO Auto-generated method stub
		rManagerList.clear();
		rManagerList = getAllManagers();
		for (RelationManager rManager : rManagerList)
		{
			if(rManager.getManagerId() == managerId) {
				return rManager;
			}
		}
		return null;
	}

	private List<RelationManager> getAllManagers() {
		// TODO Auto-generated method stub
		rManagerList.clear();
		rManagerList = managerManagedBean.getAllManagers();
		return rManagerList;
	}
}
