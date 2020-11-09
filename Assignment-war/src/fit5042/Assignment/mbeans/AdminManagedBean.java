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

import fit5042.Assignment.CRUD.AdminCRUD;
import fit5042.Assignment.entities.Admin;

@ManagedBean(name = "adminManagedBean")
@SessionScoped
public class AdminManagedBean implements Serializable{
	
	@EJB
	AdminCRUD adminCRUD;
	
	public AdminManagedBean() {
		
	}
	
	public void addAdmin(Admin admin) {
		try {
			adminCRUD.addAdmin(admin);
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public Admin searchAdminById(int adminId) {
		try {
			return adminCRUD.searchAdminById(adminId);
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<Admin> getAllAdmins(){
		try {
			List<Admin> admins = adminCRUD.getAllAdmin();
			return admins;
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public void editAdmin(Admin admin) {
		try {
			adminCRUD.editAdmin(admin);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admin updated successfully!"));
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void removeAdmin(int adminId) {
		try {
			adminCRUD.removeAdmin(adminId);
		}catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
