package fit5042.Assignment.controllers.admin;

import java.io.Serializable;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.Assignment.entities.Admin;
import fit5042.Assignment.mbeans.AdminManagedBean;

@Named("adminAddController")
@RequestScoped
public class AdminAddController implements Serializable {
	
	@ManagedProperty(value = "#{AdminManagedBean}")
	AdminManagedBean adminManagedBean;
	
	private Admin admin = new Admin();
	
	private boolean showForm = true;
	
	private boolean isShowForm() {
		return showForm;
	}
	
	AdminViewController view;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public AdminAddController() {
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		view = (AdminViewController) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(context, null, "adminViewController");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		adminManagedBean = (AdminManagedBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "adminManagedBean");
		
	}
	
	public void addAdmin(Admin newAdmin) {
		try {
			adminManagedBean.addAdmin(newAdmin);
			view.searchAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admin has been added succesfully"));
		}catch (Exception ex) {
			
		}
		showForm = true;
	}

}
