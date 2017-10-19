package com.hernan.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hernan.restapp.model.Customer;

/**
 * The Interface CustomerRepository.
 *
 * @author Hernan
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
