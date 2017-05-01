package customer.tracker.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import customer.tracker.spring.entity.Customer;
import customer.tracker.spring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//inject customer service
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel){
		
		//get customers from DAO
		List<Customer> theCustomers = customerService.getCustomer();
		
		//add customers to the model
		theModel.addAttribute("customers",theCustomers); //name value pairs
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		
		//create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
				
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")  //relationship (customer-form.jsp)=><form:form action="saveCustomer" modelAttribute="customer" method="POST">
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
		
		//save customer using my service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel){
		//get the customer from service
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set the customer as a model attribute to pre populate the form
		theModel.addAttribute("customer",theCustomer);
		
		//send over to our form
		
		return "customer-form";
	}
	
	@PostMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId){
		//delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	
}
