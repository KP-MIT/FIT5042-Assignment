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

import fit5042.Assignment.CRUD.RelationManagerCRUD;
import fit5042.Assignment.entities.RelationManager;

@ManagedBean(name = "managerManagedBean")
@SessionScoped
public class ManagerManagedBean implements Serializable{

	@EJB
	RelationManagerCRUD relationManagerCRUD;
	
	public ManagerManagedBean() {
		
	}
	
	public void addManager(RelationManager newRManager) {
		try {
			relationManagerCRUD.addManager(newRManager);
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public RelationManager searchManagerById(int managerId) {
		try {
			return relationManagerCRUD.searchManagerById(managerId);
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<RelationManager> getAllManagers(){
		try {
			List<RelationManager> managers = relationManagerCRUD.getAllManagers();
			return managers;
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public void editManager(RelationManager relationManager) {
		try {
			relationManagerCRUD.editManager(relationManager);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Manager updated succesfully!"));
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void removeManager(int managerId) {
		try {
			relationManagerCRUD.removeManager(managerId);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Manager deleted succesfully!"));
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}


