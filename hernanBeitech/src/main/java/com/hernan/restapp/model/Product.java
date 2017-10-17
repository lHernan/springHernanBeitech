package com.hernan.restapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hernan
 *
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 3818595069031438893L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true)
	private Long productId;

	@Column(name = "product_name", length = 255, nullable = false)
	private String productName;
	
	@Column(name = "product_description", length = 255, nullable = false)
	private String productDescription;

	@Digits(integer = 18, fraction = 2)
	@Column(name = "product_price", precision = 16, scale = 2, nullable = false)
	private BigDecimal productPrice;

	@JsonIgnore
	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	private Set<Customer> customers;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	private Set<Order> orders;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
}
