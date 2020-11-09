package fit5042.Assignment.controllers.manager;

import java.io.Serializable;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.RelationManager;
import fit5042.Assignment.mbeans.ManagerManagedBean;

@Named("managerEditController")
@RequestScoped
public class ManagerEditController implements Serializable {

	@ManagedProperty(value = "#{ManagerManagedBean}")
	ManagerManagedBean managerManagedBean;
	
	private int managerId=0;
	
	ManagerViewController view;
	
	private RelationManager manager;

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public fit5042.Assignment.entities.RelationManager getManager() {
		if ( manager == null) {
			return view.getManagers().get(--managerId);
		}
		return manager;
	}

	public void setManager(RelationManager manager) {
		this.manager = manager;
	}
	
	public ManagerEditController() {
		managerId = Integer.valueOf(FacesContext.getCurrentInstance()
									.getExternalContext().getRequestParameterMap()
									.get("managerID"));
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		managerManagedBean = (ManagerManagedBean) FacesContext.getCurrentInstance()
									.getApplication().getELResolver()
									.getValue(context, null, "managerManagedBean");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		view = (ManagerViewController) FacesContext.getCurrentInstance()
									.getApplication().getELResolver()
									.getValue(elContext, null, "managerViewController");
		
		manager = getManager();
	}
	
	public void editManager(RelationManager newManager) {
		try {
			managerManagedBean.editManager(newManager);
			view.searchAll();
		} catch (Exception ex) {
			
		}
	}
	
	
}
