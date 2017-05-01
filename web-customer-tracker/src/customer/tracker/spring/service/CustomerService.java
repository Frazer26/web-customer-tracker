package customer.tracker.spring.service;

import java.util.List;

import customer.tracker.spring.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomer();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
}
