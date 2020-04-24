package com.example.springboot.service;

import java.util.List;
import com.example.springboot.model.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	List<Customer> getAllCustomer();
	Customer getCustomerById(long customerId);
	void deleteCustomer(long customerId); 
}
