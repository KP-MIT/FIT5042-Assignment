package fit5042.Assignment.controllers.admin;

import java.io.Serializable;
import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Admin;
import fit5042.Assignment.mbeans.AdminManagedBean;

@Named("adminViewController")
@RequestScoped
public class AdminViewController implements Serializable {
	
	@ManagedProperty("AdminManagedBean")
	AdminManagedBean adminManagedBean;
	
	private ArrayList<Admin> admins;
	
	private int searchId;
	
	private boolean showForm = true;
	
	private boolean isShowForm() {
		return showForm;
	}
	
	public AdminViewController() {
		
		admins = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		adminManagedBean = (AdminManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "adminManagedBean");
		
		updateAdminList();
	}

	private void updateAdminList() {
		// TODO Auto-generated method stub
		if (admins != null && admins.size()>0)
		{
			
		}
		else {
			admins.clear();
			
			for (fit5042.Assignment.entities.Admin admin : adminManagedBean.getAllAdmins())
			{
				admins.add(admin);
			}
			setAdmins(admins);
		}
	}

	public ArrayList<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<Admin> admins) {
		this.admins = admins;
	}
	
	public void searchAll() {
		admins.clear();
		for (fit5042.Assignment.entities.Admin admin : adminManagedBean.getAllAdmins())
		{
			admins.add(admin);
			setAdmins(admins);
		}
	}
	
	public void searchAdminById(int adminId) {
		admins.clear();
		admins.add(adminManagedBean.searchAdminById(adminId));
	}

	public void removeAdmin(int adminId) {
		
		adminManagedBean.removeAdmin(adminId);
		searchAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admin has been succesfully deleted."));
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	
	
}
