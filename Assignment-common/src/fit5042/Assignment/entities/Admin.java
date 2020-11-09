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
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "Admin")
@NamedQueries({
	@NamedQuery(name = Admin.GET_ALL_QUERY_NAME, query = "SELECT c From Admin c")
}
		)
public class Admin implements Serializable{
	
	public static final String GET_ALL_QUERY_NAME = "SELECT * FROM Admin";
	
	private int adminId;
	private String adminName;
	@Email
	private String email;
	private String phoneNumber;
	private String adminUserName;
	private String dateOfBirth;
	private Set<String> tags;
	
	public Admin() {
		this.tags = new HashSet<>();
		
	}
	
	public Admin(int adminId, String adminName, String email, String phoneNumber, String adminUserName,
			String dateOfBirth, Set<String> tags) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adminUserName = adminUserName;
		this.dateOfBirth = dateOfBirth;
		this.tags = tags;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "admin_id")
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", adminUserName=" + adminUserName + ", dateOfBirth=" + dateOfBirth + ", tags=" + tags
				+ "]";
	}
	
}
