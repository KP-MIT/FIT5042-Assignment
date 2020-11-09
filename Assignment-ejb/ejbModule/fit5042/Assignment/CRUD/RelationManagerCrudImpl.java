package fit5042.Assignment.CRUD;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fit5042.Assignment.entities.RelationManager;

@Stateless
public class RelationManagerCrudImpl implements RelationManagerCRUD{

	@PersistenceContext(unitName = "Assignment-ejbPU")
	private EntityManager entityManager;
	
	@Override
	public void addManager(RelationManager relationManager) throws Exception {
		// TODO Auto-generated method stub
		List<RelationManager> relationManagers = entityManager.createNamedQuery(RelationManager.GET_ALL_QUERY_NAME).getResultList();
		relationManager.setManagerId(relationManagers.get(0).getManagerId()+1);
		entityManager.persist(relationManager);
	}

	@Override
	public RelationManager searchManagerById(int managerId) throws Exception {
		// TODO Auto-generated method stub
		RelationManager relationManager = entityManager.find(RelationManager.class, managerId);
		return relationManager;
	}

	@Override
	public List<RelationManager> getAllManagers() throws Exception {
		// TODO Auto-generated method stub
		List<RelationManager> managerList = entityManager.createNamedQuery(RelationManager.GET_ALL_QUERY_NAME).getResultList();
		return managerList;
	}

	@Override
	public void editManager(RelationManager relationManager) throws Exception {
		// TODO Auto-generated method stub
		try {
			entityManager.merge(relationManager);
		}catch (Exception ex) {
			
		}
	}

	@Override
	public void removeManager(int managerId) throws Exception {
		// TODO Auto-generated method stub
		RelationManager relationManager = entityManager.find(RelationManager.class, managerId);
		entityManager.remove(relationManager);
		
	}

}
