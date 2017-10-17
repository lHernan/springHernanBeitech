package com.hernan.restapp.service;

import java.util.Date;
import java.util.List;

import com.hernan.restapp.model.Order;

/**
 * @author Hernan
 *
 */
public interface OrderService extends CRUDService<Order> {

	List<Order> getAllOrdersByCustomerId(Long customerId, Date initialDate, Date finalDate);
	
}
