package fit5042.Assignment.CRUD;

import java.util.List;

import javax.ejb.Remote;

import fit5042.Assignment.entities.Admin;

@Remote
public interface AdminCRUD {

	public void addAdmin(Admin admin) throws Exception;
	
	public Admin searchAdminById(int adminId) throws Exception;
	
	public List<Admin> getAllAdmin() throws Exception;

	public void editAdmin(Admin admin) throws Exception;
	
	public void removeAdmin(int adminId) throws Exception;

}
