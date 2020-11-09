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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="CUSTOMER")
@NamedQueries({
	@NamedQuery(name = Customer.GET_ALL_QUERY_NAME, query = "SELECT c FROM Customer c")
})
public class Customer implements Serializable{
	
	public static final String GET_ALL_QUERY_NAME = "SELECT * FROM Customer";
	
	private int customerId;
	@NotNull
	private String customerName;
	private String address;
	@NotNull
	private String industry;
	private String country;
	private double unitPurchased;
	@NotNull
	private String joinDate;
	
	private RelationManager relationManager;
	private Set<Contact> contact;
	private Set<String> tags;
	
	public Customer() {
		this.tags = new HashSet<>();
	}

	public Customer(int customerId, String customerName, String address, String industry, String country,
			double unitPurchased, String joinDate, RelationManager relationManager, Set<Contact> contact,
			Set<String> tags) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.industry = industry;
		this.country = country;
		this.unitPurchased = unitPurchased;
		this.joinDate = joinDate;
		this.relationManager = relationManager;
		this.contact = new HashSet<>();
		this.tags = tags;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "customer_id")
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getUnitPurchased() {
		return unitPurchased;
	}

	public void setUnitPurchased(double unitPurchased) {
		this.unitPurchased = unitPurchased;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	@ManyToOne
	public RelationManager getRelationManager() {
		return relationManager;
	}

	public void setRelationManager(RelationManager relationManager) {
		this.relationManager = relationManager;
	}
	
	@OneToMany(mappedBy = "customer")
	public Set<Contact> getContact() {
		return contact;
	}

	public void setContact(Set<Contact> contact) {
		this.contact = contact;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "TAG")
	@Column(name = "VALUE")
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
				+ ", industry=" + industry + ", country=" + country + ", unitPurchased=" + unitPurchased + ", joinDate="
				+ joinDate + ", relationManager=" + relationManager + ", contact=" + contact + "]";
	}
	
	
	
}
