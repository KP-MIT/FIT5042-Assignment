package fit5042.Assignment.CRUD;

import java.util.List;

import javax.ejb.Remote;

import fit5042.Assignment.entities.RelationManager;

@Remote
public interface RelationManagerCRUD {

	public void addManager(RelationManager relationManager) throws Exception;
	
	public RelationManager searchManagerById(int managerId) throws Exception;
	
	public List<RelationManager> getAllManagers() throws Exception;

	public void editManager(RelationManager relationManager) throws Exception;
	
	public void removeManager(int managerId) throws Exception;

}
