package fit5042.Assignment.CRUD;

import java.util.List;

import javax.ejb.Remote;

import fit5042.Assignment.entities.Contact;
import fit5042.Assignment.entities.Customer;

@Remote
public interface CustomerCRUD {
	
	public void addCustomer(Customer customer) throws Exception;
	
	public Customer searchCustomerById(int customerId) throws Exception;
	
	public List<Customer> SearchCustomerByUnitPurchased(double unitPurchased) throws Exception;
	
	public List<Customer> getAllCustomer() throws Exception;

	public void editCustomer(Customer customer) throws Exception;
	
	public void removeCustomer(int customerId) throws Exception;
}
