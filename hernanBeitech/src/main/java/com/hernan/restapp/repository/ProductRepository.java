package com.hernan.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hernan.restapp.model.Product;

/**
 * The Interface ProductRepository.
 *
 * @author Hernan
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
