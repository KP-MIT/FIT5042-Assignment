package fit5042.Assignment.CRUD;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fit5042.Assignment.entities.Contact;
import fit5042.Assignment.entities.Customer;

@Stateless
public class CustomerCrudImpl implements CustomerCRUD{
	
	@PersistenceContext(unitName = "Assignment-ejbPU")
	private EntityManager entityManager;

	@Override
	public void addCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		List<Customer> customers = entityManager.createNamedQuery(Customer.GET_ALL_QUERY_NAME).getResultList();
		customer.setCustomerId(customers.get(0).getCustomerId());
		entityManager.persist(customer);
		
	}

	@Override
	public Customer searchCustomerById(int customerId) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = entityManager.find(Customer.class, customerId);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomer() throws Exception {
		// TODO Auto-generated method stub
		List<Customer> customerList = entityManager.createNamedQuery(Customer.GET_ALL_QUERY_NAME).getResultList();
		return customerList;
	}

	@Override
	public void editCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		try {
			entityManager.merge(customer);
		} catch (Exception ex){
			
		}
		
	}

	@Override
	public void removeCustomer(int customerId) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = entityManager.find(Customer.class,customerId);
		entityManager.remove(customer);
	}

	@Override
	public List<Customer> SearchCustomerByUnitPurchased(double unitPurchased) throws Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(Customer.class);
		Root<Customer> p = query.from(Customer.class);
		query.select(p).where(builder.lessThanOrEqualTo(p.get("unitPurchased").as(Double.class), unitPurchased));
		List<Customer> searchResult = entityManager.createQuery(query).getResultList();
		return searchResult;
	}


}
