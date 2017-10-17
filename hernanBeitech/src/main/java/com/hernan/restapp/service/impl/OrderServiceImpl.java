package com.hernan.restapp.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernan.restapp.model.Customer;
import com.hernan.restapp.model.Order;
import com.hernan.restapp.model.Product;
import com.hernan.restapp.repository.CustomerRepository;
import com.hernan.restapp.repository.OrderRepository;
import com.hernan.restapp.repository.ProductRepository;
import com.hernan.restapp.service.OrderService;

/**
 * 
 * @author Hernan
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Integer MAX_ALLOWED_PRODUCTS = 5;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Order save(Order order) {
		
		if(order.getProducts().size() >= MAX_ALLOWED_PRODUCTS) {
			throw new IllegalArgumentException("Max allowed products exceeded");
		}
		
		Customer customer = customerRepository.findOne(order.getCustomer().getCustomerId());
		order.setCustomer(customer);
		
		Set<Product> products = new HashSet<>();
		
		for(Product orderProduct : order.getProducts()) {
			
			if(!customer.isProductAvailable(orderProduct.getProductId())) {
				throw new IllegalArgumentException("Product with id [" + orderProduct.getProductId() + "] not allowed for customer + [" + customer.getCustomerId() + "]");
			}
			
			Product product = productRepository.getOne(orderProduct.getProductId());
			products.add(product);
		}
		
		order.setProducts(products);
		order.setDate(new Date());
		
		return orderRepository.save(order);
	}

	@Override
	public Order getById(Serializable id) {
		return orderRepository.findOne((Long) id);
	}

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		orderRepository.delete((Long) id);
	}

	@Override
	public List<Order> getAllOrdersByCustomerId(Long customerId, Date initialDate, Date finalDate) {

		return orderRepository.getAllOrdersByCustomer(customerId, initialDate, finalDate);
	}

}
