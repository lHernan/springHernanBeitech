package com.hernan.restapp.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernan.restapp.model.Customer;
import com.hernan.restapp.model.Order;
import com.hernan.restapp.model.OrderDetail;
import com.hernan.restapp.model.Product;
import com.hernan.restapp.repository.CustomerRepository;
import com.hernan.restapp.repository.OrderRepository;
import com.hernan.restapp.repository.ProductRepository;
import com.hernan.restapp.service.OrderService;

/**
 * implementation of order service.
 *
 * @author Hernan
 */
@Service
public class OrderServiceImpl implements OrderService {

	/** The Constant MAX_ALLOWED_PRODUCTS. */
	private static final Integer MAX_ALLOWED_PRODUCTS = 5;

	/** The order repository. */
	@Autowired
	private OrderRepository orderRepository;

	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Save.
	 *
	 * @param order
	 *            the order
	 * @return the order
	 */
	@Override
	public Order save(Order order) {

		Customer customer = customerRepository.findOne(order.getCustomer().getCustomerId());
		order.setCustomer(customer);

		Set<OrderDetail> orderDetails = order.getOrderDetails();

		BigDecimal orderPrice = new BigDecimal(0);

		for (OrderDetail orderDetail : orderDetails) {

			if (orderDetail.getProductQuantity() >= MAX_ALLOWED_PRODUCTS) {
				throw new IllegalArgumentException("Max allowed products exceeded");
			}
			Long productId = orderDetail.getProduct().getProductId();

			if (!customer.isProductAvailable(productId)) {
				throw new IllegalArgumentException("Product with id [" + productId + "] not allowed for customer + ["
						+ customer.getCustomerId() + "]");
			}

			Product product = productRepository.getOne(productId);
			orderDetail.setProduct(product);
			orderDetail.setOrder(order);

			orderPrice = orderPrice.add(product.getProductPrice());
		}

		order.setOrderPrice(orderPrice);
		order.setDate(new Date());

		return orderRepository.save(order);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	@Override
	public Order getById(Serializable id) {
		return orderRepository.findOne((Long) id);
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	@Override
	public void delete(Serializable id) {
		orderRepository.delete((Long) id);
	}

	/**
	 * Gets the all orders by customer id.
	 *
	 * @param customerId
	 *            the customer id
	 * @param initialDate
	 *            the initial date
	 * @param finalDate
	 *            the final date
	 * @return the all orders by customer id
	 */
	@Override
	public List<Order> getAllOrdersByCustomerId(Long customerId, Date initialDate, Date finalDate) {

		return orderRepository.getAllOrdersByCustomer(customerId, initialDate, finalDate);
	}

}
