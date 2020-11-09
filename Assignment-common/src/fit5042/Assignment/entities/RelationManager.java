package fit5042.Assignment.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Relation_manager")
@NamedQueries({
	@NamedQuery(name = RelationManager.GET_ALL_QUERY_NAME, query = "SELECT c FROM RelationManager c")
})
public class RelationManager implements Serializable{
	
	public static final String GET_ALL_QUERY_NAME = "SELECT * FROM RelationManager";

	private int managerId;
	private String managerName;
	private String dept;
	
	private Set<Customer> customer;
	
	public RelationManager() {
		
	}
	
	public RelationManager(int managerId, String managerName, String dept) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.dept = dept;
		this.customer = new HashSet<>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "manager_id")
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	@OneToMany(mappedBy = "relationManager")
	public Set<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "RelationManager [managerId=" + managerId + ", managerName=" + managerName + ", dept=" + dept
				+ ", customer="+ customer + "]";
	}
	
	
}
