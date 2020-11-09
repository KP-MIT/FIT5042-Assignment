package fit5042.Assignment.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "CONTACT")
@NamedQueries({
	@NamedQuery(name = Contact.GET_ALL_QUERY_NAME, query = "SELECT c FROM Contact c")})
public class Contact implements Serializable{

		public static final String GET_ALL_QUERY_NAME = "SELECT * FROM Contact";
		
		private int contactId;
		private ContactName name;
		@Email
		private String email;
		private String phoneNumber;
		private String address;
		
		private Customer customer;
		private Set<String> tags;

		public Contact() {
			this.tags = new HashSet<>();
		}
		
		
		
		public Contact(int contactId, ContactName name, String email, String phoneNumber, String address, Customer customer
				, Set<String> tags) {
			super();
			this.contactId = contactId;
			this.name = name;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.customer = customer;
			this.tags = tags;
		}



		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Column(name = "contact_id")
		public int getContactId() {
			return contactId;
		}

		public void setContactId(int contactId) {
			this.contactId = contactId;
		}
		
		@Embedded
		public ContactName getName() {
			return name;
		}

		public void setName(ContactName contactName) {
			this.name = contactName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Column(name="phone_number")
		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		@ManyToOne
		public Customer getCustomer() {
			return customer;
		}
		
		public void setCustomer(Customer customer) {
			this.customer = customer;
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

		
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "Contact [contactId=" + contactId + ", name=" + name + ", email=" + email + ", phoneNumber="
					+ phoneNumber + ", customer=" + customer + ", tags=" + tags + "]";
		}
		
		
		
		
		
}
