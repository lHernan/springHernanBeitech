package com.hernan.restapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hernan.restapp.model.Order;


/**
 * The Interface OrderRepository.
 *
 * @author Hernan
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	/**
	 * Gets the all orders by customer.
	 *
	 * @param customerId
	 *            the customer id
	 * @param initialDate
	 *            the initial date
	 * @param finalDate
	 *            the final date
	 * @return the all orders by customer
	 */
	@Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId AND o.date BETWEEN :initialDate AND :finalDate")
	List<Order> getAllOrdersByCustomer(@Param("customerId") Long customerId, @Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate);

}
