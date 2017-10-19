package com.hernan.restapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hernan.restapp.model.Customer;
import com.hernan.restapp.service.CustomerService;

/**
 * The Class CustomerController.
 */
@RestController
@RequestMapping("/rest/customer")
public class CustomerController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	/** The customer service. */
	@Autowired
	private CustomerService customerService;

	/**
	 * Gets the customer by id.
	 *
	 * @param customerId
	 *            the customer id
	 * @return the customer by id
	 */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {

		Customer customer = customerService.getById(customerId);
		if (customer == null) {
			logger.debug("Customer with id " + customerId + " does not exist");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Customer:: " + customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	/**
	 * Gets the all customers.
	 *
	 * @return all customers
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomers() {

		List<Customer> customers = customerService.getAll();
		if (customers.isEmpty()) {
			logger.debug("There are no customers");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

}
