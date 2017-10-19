package com.hernan.restapp.service;

import java.util.Date;
import java.util.List;

import com.hernan.restapp.model.Order;

/**
 * The Interface OrderService.
 *
 * @author Hernan
 */
public interface OrderService extends CRUDService<Order> {

	/**
	 * method to get all orders by the id of a customer on data range.
	 *
	 * @param customerId
	 *            the customer id
	 * @param initialDate
	 *            the initial date
	 * @param finalDate
	 *            the final date
	 * @return the all orders by customer id
	 */
	List<Order> getAllOrdersByCustomerId(Long customerId, Date initialDate, Date finalDate);

}
