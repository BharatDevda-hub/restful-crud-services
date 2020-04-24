package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Customer;
import com.example.springboot.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> customerDb = customerRepository.findById(customer.getId());
		if (customerDb.isPresent()) {
			Customer customerUpdate = customerDb.get();
			customerUpdate.setId(customer.getId());
			customerUpdate.setName(customer.getName());
			customerUpdate.setAddress(customer.getAddress());
			customerUpdate.setMobileno(customer.getMobileno());
			customerUpdate.setDob(customer.getDob());
			customerRepository.save(customerUpdate);
			return customerUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + customer.getId());
		}

	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}

	@Override
	public Customer getCustomerById(long customerId) {
		Optional<Customer> customerDb = customerRepository.findById(customerId);
		if (customerDb.isPresent()) {
			return customerDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + customerId);
		}
	}

	@Override
	public void deleteCustomer(long customerId) {
		Optional<Customer> customerDb = customerRepository.findById(customerId);
		if (customerDb.isPresent()) {
			customerRepository.delete(customerDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + customerId);
		}

	}

}
