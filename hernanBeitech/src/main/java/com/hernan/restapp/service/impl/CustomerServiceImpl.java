package com.hernan.restapp.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernan.restapp.model.Customer;
import com.hernan.restapp.repository.CustomerRepository;
import com.hernan.restapp.service.CustomerService;

/**
 * 
 * @author Hernan
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer save(Customer entity) {
		return customerRepository.save(entity);
	}

	@Override
	public Customer getById(Serializable id) {
		return customerRepository.findOne((Long) id);
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		customerRepository.delete((Long) id);
	}

}
