package fit5042.Assignment.controllers.admin;

import java.io.Serializable;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Admin;
import fit5042.Assignment.mbeans.AdminManagedBean;

@Named("adminEditController")
@RequestScoped
public class AdminEditController implements Serializable {

	@ManagedProperty(value = "#{AdminManagedBean}")
	AdminManagedBean adminManagedBean;
	
	private int adminId=0;
	
	AdminViewController view;
	
	private Admin admin;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public fit5042.Assignment.entities.Admin getAdmin() {
		if (admin == null) {
			return view.getAdmins().get(--adminId);
		}
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public AdminEditController() {
		adminId = Integer.valueOf(FacesContext.getCurrentInstance()
									.getExternalContext().getRequestParameterMap()
									.get("adminID"));
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		adminManagedBean = (AdminManagedBean) FacesContext.getCurrentInstance()
									.getApplication().getELResolver()
									.getValue(context, null, "adminManagedBean");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		view = (AdminViewController) FacesContext.getCurrentInstance()
									.getApplication().getELResolver()
									.getValue(elContext, null, "adminViewController");
		
		admin = getAdmin();
	}
	
	public void editAdmin(Admin newAdmin) {
		try {
			adminManagedBean.editAdmin(newAdmin);
			view.searchAll();
		} catch (Exception ex) {
			
		}
	}
	
	
}
