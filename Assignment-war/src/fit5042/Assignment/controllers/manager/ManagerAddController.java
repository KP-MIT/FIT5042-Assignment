package fit5042.Assignment.controllers.manager;

import java.io.Serializable;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.RelationManager;
import fit5042.Assignment.mbeans.ManagerManagedBean;

@Named("managerAddController")
@RequestScoped
public class ManagerAddController implements Serializable {
	
	@ManagedProperty(value = "#{ManagerManagedBean}")
	ManagerManagedBean managerManagedBean;
	
	private RelationManager manager = new RelationManager();
	
	private boolean showForm = true;
	
	private boolean isShowForm() {
		return showForm;
	}
	
	ManagerViewController view;

	public RelationManager getManager() {
		return manager;
	}

	public void setManager(RelationManager manager) {
		this.manager = manager;
	}
	
	public ManagerAddController() {
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		view = (ManagerViewController) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(context, null, "managerViewController");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		managerManagedBean = (ManagerManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "managerManagedBean");
		
	}
	
	public void addManager(RelationManager newManager) {
		try {
			managerManagedBean.addManager(newManager);
			view.searchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Manager has been added succesfully"));
		}catch (Exception ex) {
			
		}
		showForm = true;
	}

}
