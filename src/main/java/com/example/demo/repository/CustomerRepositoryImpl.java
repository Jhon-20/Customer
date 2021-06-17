package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Region;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositiry{

	private List<Customer> customers = new ArrayList<Customer>();
	private List<Region> regiones = new ArrayList<Region>();
	
	private void init() {
		Region region1 = new Region(1L,"Apurimac");
		Region region2 = new Region(2L,"Cuzco");
		Region region3 = new Region(3L,"Puno");
		Region region4 = new Region(4L,"Tacna");
		Region region5 = new Region(5L,"Piura");
		
		regiones.add(region1);
		regiones.add(region2);
		regiones.add(region3);
		regiones.add(region4);
		regiones.add(region5);
		
		Customer c1 = new Customer(1L,"75785487","Pedro","Benites","desarrollador","",region1,"CREATED");
		customers.add(c1);
	}
	
	public CustomerRepositoryImpl() {
	init();
	}
	
	@Override
	public List<Customer> findAll() {
		return customers;
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customers.stream().filter(p -> p.getId() == id).findFirst();
	}

	@Override
	public <S extends Customer> S save(S entity) {
		customers.add(entity);
		return entity;
	}

	@Override
	public List<Customer> findByRegion(Region region) {
		return customers.stream().filter(p -> p.getRegion().getId() ==
				region.getId()).collect(Collectors.toList()) ;
	}

	@Override
	public Customer findByNumberId(String numberId) {
		return customers.stream().filter(p -> p.getNumberId().equals(numberId)).findAny().orElse(null);
	}

}
