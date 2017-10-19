package com.hernan.restapp.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernan.restapp.model.Customer;
import com.hernan.restapp.repository.CustomerRepository;
import com.hernan.restapp.service.CustomerService;

/**
 * implementation of customerService.
 *
 * @author Hernan
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hernan.restapp.service.CRUDService#save(java.lang.Object)
	 */
	@Override
	public Customer save(Customer entity) {
		return customerRepository.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hernan.restapp.service.CRUDService#getById(java.io.Serializable)
	 */
	@Override
	public Customer getById(Serializable id) {
		return customerRepository.findOne((Long) id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hernan.restapp.service.CRUDService#getAll()
	 */
	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hernan.restapp.service.CRUDService#delete(java.io.Serializable)
	 */
	@Override
	public void delete(Serializable id) {
		customerRepository.delete((Long) id);
	}

}
