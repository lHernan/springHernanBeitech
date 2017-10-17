package com.hernan.restapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hernan.restapp.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId AND o.date BETWEEN :initialDate AND :finalDate")
	List<Order> getAllOrdersByCustomer(
			@Param("customerId") Long customerId,
			@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate);

}
