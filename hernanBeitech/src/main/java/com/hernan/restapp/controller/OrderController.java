package com.hernan.restapp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hernan.restapp.model.Order;
import com.hernan.restapp.service.OrderService;

/**
 * The Class OrderController.
 */
@RestController
@RequestMapping("/rest/order")
public class OrderController {

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(OrderController.class);

	/** The order service. */
	@Autowired
	private OrderService orderService;

	/**
	 * method to add order.
	 *
	 * @param order
	 *            the order
	 * @return the created order
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		orderService.save(order);
		logger.debug("Added:: " + order);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}

	/**
	 * method do get order by id.
	 *
	 * @param id
	 *            the id
	 * @return order found by id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
		Order order = orderService.getById(id);
		if (order == null) {
			logger.debug("Order with id " + id + " does not exists");
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Order:: " + order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	/**
	 * method to get the orders of a customer by data range.
	 *
	 * @param customerId
	 *            the customer id
	 * @param initialDate
	 *            the initial date
	 * @param finalDate
	 *            the final date
	 * @return the all orders by customer
	 */
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getAllOrdersByCustomer(@PathVariable("customerId") Long customerId,
			@RequestParam("initialDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date initialDate,
			@RequestParam("finalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date finalDate) {

		List<Order> orders = orderService.getAllOrdersByCustomerId(customerId, initialDate, finalDate);
		if (orders.isEmpty()) {
			logger.debug("Orders does not exists");
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		}

		logger.debug("Found " + orders.size() + " orders");
		logger.debug(Arrays.toString(orders.toArray()));
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

}
