package com.hernan.restapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity product.
 *
 * @author Hernan
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3818595069031438893L;

	/** The product id. */
	private Long productId;

	/** The product name. */
	private String productName;

	/** The product description. */
	private String productDescription;

	/** The product price. */
	private BigDecimal productPrice;

	/** The customers. */
	@JsonIgnore
	private Set<Customer> customers;

	/** The order details. */
	@JsonIgnore
	private Set<OrderDetail> orderDetails;

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	public Long getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId
	 *            the new product id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	@Column(name = "product_name", length = 255, nullable = false)
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName
	 *            the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the product description.
	 *
	 * @return the product description
	 */
	@Column(name = "product_description", length = 255, nullable = false)
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * Sets the product description.
	 *
	 * @param productDescription
	 *            the new product description
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * Gets the product price.
	 *
	 * @return the product price
	 */
	@Digits(integer = 18, fraction = 2)
	@Column(name = "product_price", precision = 16, scale = 2, nullable = false)
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	/**
	 * Sets the product price.
	 *
	 * @param productPrice
	 *            the new product price
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 */
	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	public Set<Customer> getCustomers() {
		return customers;
	}

	/**
	 * Sets the customers.
	 *
	 * @param customers
	 *            the new customers
	 */
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * Gets the order details.
	 *
	 * @return the order details
	 */
	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	/**
	 * Sets the order details.
	 *
	 * @param orderDetails
	 *            the new order details
	 */
	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	};

}
