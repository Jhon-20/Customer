package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Region;
import com.example.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> listCustomers(
			@RequestParam(name = "regionId", required = false) Long regionId) {

		List<Customer> customers = new ArrayList<>();
		if (null == regionId) {
			customers = customerService.findCustomerAll();
			if (customers.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			Region region = new Region();
			region.setId(regionId);
			customers = customerService.findCustomersByRegion(region);
			if (null == customers) {
				log.error("Client with region{} not found", regionId);
				return ResponseEntity.notFound().build();
			}

		}
		return ResponseEntity.ok(customers);
	}

	@GetMapping(value = "{/id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
		log.info("Fetching Customer with id {}", id);
		Customer customer = customerService.getCustomer(id);
		if (null == customer) {
			log.error("Customer with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer, BindingResult result) {
		log.info("Creating Customer : {}", customer);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Customer BD = customerService.createCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(BD);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		log.info("Updating Customer with id {}", id);
		Customer currentCustomer = customerService.getCustomer(id);
		if (null == currentCustomer) {
			log.error("Unable to update. Customer with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		customer.setId(id);
		currentCustomer = customerService.updateCustomer(customer);
		return ResponseEntity.ok(currentCustomer);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
		log.info("Fetching & Deleting Customer with id {}", id);
		Customer customer = customerService.getCustomer(id);
		if (null == customer) {
			log.error("Unable to delete. Customer with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		customer = customerService.deleteCustomer(customer);
		return ResponseEntity.ok(customer);
	}
}
