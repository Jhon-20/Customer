package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Region;
import com.example.demo.repository.CustomerRepositiry;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepositiry repository;
	
	@Override
	public List<Customer> findCustomerAll() {
		return repository.findAll();
	}

	@Override
	public List<Customer> findCustomersByRegion(Region region) {
		return repository.findByRegion(region);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		Customer BD = repository.findByNumberId(customer.getNumberId());
		if(BD != null) {
			return BD;
		}
		customer.setState("CREATED");
		BD = repository.save(customer);
		return BD;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer BD = getCustomer(customer.getId());
		if(BD == null) {
			return null;	
		}
		BD.setFirstName(customer.getFirstName());
		BD.setLasName(customer.getLasName());
		BD.setEmail(customer.getEmail());
		BD.setPhtoUrl(customer.getPhtoUrl());
		return repository.save(BD);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		Customer BD = getCustomer(customer.getId());
		if(BD == null) {
			return null;	
		}
		customer.setState("DELETED");
		return BD = repository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		return repository.findById(id).orElse(null);
	}

	
}
