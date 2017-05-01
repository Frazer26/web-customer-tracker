package customer.tracker.spring.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import customer.tracker.spring.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO { //dao representation data access object
	
	//need inject the session factory (talking to database)
	@Autowired
	private SessionFactory sessionFactory; //spring-mvc-crud-demo-servlet.xml-ben lévő sessionFactoryhez kapcsolódik

	@Override
	public List<Customer> getCustomer() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query.. sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
																Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save or update the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession =sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}

}
