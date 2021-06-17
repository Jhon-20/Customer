package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Region;


public interface CustomerRepositiry {
	public List<Customer> findAll();
	public Optional<Customer> findById(Long id);
	public <S extends Customer> S save(S entity);
	public List<Customer> findByRegion(Region region);
	public Customer findByNumberId(String numberId);
}
