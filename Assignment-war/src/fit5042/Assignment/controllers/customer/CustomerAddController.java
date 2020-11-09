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

@Named("customerAddController")
@RequestScoped
public class CustomerAddController implements Serializable {
	
	@ManagedProperty(value = "#{CustomerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	@ManagedProperty(value = "#{ManagerManagedBean}")
	ManagerManagedBean managerManagedBean;
	
	private Customer customer = new Customer();
	
	private List<RelationManager> rManagerList = new ArrayList<>();
	
	int managerId=0;
	
	private boolean showForm = true;
	
	CustomerViewController view;
	
	private List<String> industries = new ArrayList<String>();
	
	private String industry="";
	
	public List<RelationManager> getrManagerList() {
		return rManagerList;
	}

	public void setrManagerList(List<RelationManager> rManagerList) {
		this.rManagerList = rManagerList;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public List<String> getIndustries() {
		return industries;
	}

	public void setIndustries(List<String> industries) {
		this.industries = industries;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public CustomerAddController() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		view = (CustomerViewController) FacesContext.getCurrentInstance()
				.getApplication()
				.getELResolver()
				.getValue(context, null, "customerViewController");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
							.getApplication()
							.getELResolver()
							.getValue(elContext, null, "customerManagedBean");
		
		ELContext con = FacesContext.getCurrentInstance().getELContext();
		
		managerManagedBean = (ManagerManagedBean) FacesContext.getCurrentInstance()
							.getApplication().getELResolver()
							.getValue(con, null, "managerManagedBean");
		
		industries = Arrays.asList("Bank","Building","Data Communication","Education"
				,"Farm","Health","Mining","Publishing");
	}
	
	public void addCustomer(Customer newCustomer) {
		managerId = getManagerId();
		industry = getIndustry();
		newCustomer.setIndustry(industry);
		RelationManager rManager = findManagerById(managerId);
		newCustomer.setRelationManager(rManager);
		try {
			customerManagedBean.addCustomer(newCustomer);
			view.searchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been added succesfully"));
		}catch (Exception ex) {
			
		}
		showForm = true;
	}

	private RelationManager findManagerById(int managerId) {
		// TODO Auto-generated method stub
		rManagerList.clear();
		rManagerList = getAllManagerList();
		for (RelationManager rManager : rManagerList)
		{
			if (rManager.getManagerId() == managerId) {
				return rManager;
			}
		}
		return null;
	}

	private List<RelationManager> getAllManagerList() {
		// TODO Auto-generated method stub
		rManagerList.clear();
		rManagerList = managerManagedBean.getAllManagers();
		return rManagerList;
	}
}
