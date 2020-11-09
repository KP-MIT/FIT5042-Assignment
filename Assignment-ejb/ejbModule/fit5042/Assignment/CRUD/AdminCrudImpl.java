package fit5042.Assignment.CRUD;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fit5042.Assignment.entities.Admin;

@Stateless
public class AdminCrudImpl implements AdminCRUD{
	
	@PersistenceContext(unitName = "Assignment-ejbPU")
	private EntityManager entityManager;

	@Override
	public void addAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub		
		List<Admin> admins = entityManager.createNamedQuery(Admin.GET_ALL_QUERY_NAME).getResultList();
		admin.setAdminId(admins.get(0).getAdminId()+1);
		entityManager.persist(admin);
		
	}

	@Override
	public Admin searchAdminById(int adminId) throws Exception {
		// TODO Auto-generated method stub
		Admin admin = entityManager.find(Admin.class,adminId);
		return admin;
	}

	@Override
	public List<Admin> getAllAdmin() throws Exception {
		// TODO Auto-generated method stub
		List<Admin> adminList = entityManager.createNamedQuery(Admin.GET_ALL_QUERY_NAME).getResultList();
		return adminList;
	}

	@Override
	public void editAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		try{
			entityManager.merge(admin);
		}catch (Exception ex) {
			
		}
	}

	@Override
	public void removeAdmin(int adminId) throws Exception {
		// TODO Auto-generated method stub
		Admin admin = entityManager.find(Admin.class,adminId);
		entityManager.remove(admin);
	}

}
