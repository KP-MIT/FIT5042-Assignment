package fit5042.Assignment.controllers.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Customer;
import fit5042.Assignment.entities.RelationManager;
import fit5042.Assignment.mbeans.ManagerManagedBean;

@Named("managerViewController")
@RequestScoped
public class ManagerViewController implements Serializable {
	
	@ManagedProperty("ManagerManagedBean")
	ManagerManagedBean managerManagedBean;
	
	private ArrayList<RelationManager> rManagers;
	
	private int searchId;
	
	private boolean showForm = true;
	
	private boolean isShowForm() {
		return showForm;
	}
	
	public ManagerViewController() {
		
		
		rManagers = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		managerManagedBean = (ManagerManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "managerManagedBean");
		
		updateManagerList();
	}

	private void updateManagerList() {
		// TODO Auto-generated method stub
			rManagers.clear();
			
			for (fit5042.Assignment.entities.RelationManager rManager : managerManagedBean.getAllManagers())
			{
				rManagers.add(rManager);
			}
			setManagers(rManagers);
	}

	public ArrayList<RelationManager> getManagers() {
		return rManagers;
	}

	public void setManagers(ArrayList<RelationManager> rManagers) {
		this.rManagers = rManagers;
	}
	
	public void searchAll() {
		rManagers.clear();
		for (fit5042.Assignment.entities.RelationManager rManager : managerManagedBean.getAllManagers())
		{
			rManagers.add(rManager);
		}
		setManagers(rManagers);
	}
	
	public void searchManagerById(int managerId) {
		rManagers.clear();
		rManagers.add(managerManagedBean.searchManagerById(managerId));
	}

	public void removeManager(int managerId) {
		
		managerManagedBean.removeManager(managerId);
		searchAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Manager has been succesfully deleted."));
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	
}
