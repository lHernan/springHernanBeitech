package com.hernan.restapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity customer.
 *
 * @author Hernan
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1250472524813878477L;

	/** The customer id. */
	private Long customerId;

	/** The customer name. */
	private String customerName;

	/** The customer email. */
	private String customerEmail;

	/** The products. */
	private Set<Product> products;

	/** The orders. */
	@JsonIgnore
	private Set<Order> orders;

	/**
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer id.
	 *
	 * @param customerId
	 *            the new customer id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	@Column(name = "customer_name", length = 255, nullable = false)
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName
	 *            the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the customer email.
	 *
	 * @return the customer email
	 */
	@Column(name = "customer_email", length = 255, nullable = false)
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * Sets the customer email.
	 *
	 * @param customerEmail
	 *            the new customer email
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "product_customer", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * Sets the products.
	 *
	 * @param products
	 *            the new products
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	@OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	public Set<Order> getOrders() {
		return orders;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders
	 *            the new orders
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", products=" + products + ", orders=" + orders + "]";
	}

	/**
	 * Checks if is product available.
	 *
	 * @param productId
	 *            the product id
	 * @return true, if is product available
	 */
	public boolean isProductAvailable(Long productId) {

		for (Product product : products) {
			if (product.getProductId().equals(productId)) {
				return true;
			}
		}

		return false;
	}

}
