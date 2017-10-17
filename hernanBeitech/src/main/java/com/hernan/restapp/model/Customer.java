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

/**
 * @author Hernan
 *
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1250472524813878477L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	private Long customerId;

	@Column(name = "customer_name", length = 255, nullable = false)
	private String customerName;

	@Column(name = "customer_email", length = 255, nullable = false)
	private String customerEmail;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "product_customer", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
	private Set<Product> products;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Order> orders; // ok

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", products=" + products + ", orders=" + orders + "]";
	}
	
	public boolean isProductAvailable(Long productId) {
		
		for(Product product : products) {
			if(product.getProductId().equals(productId)) {
				return true;
			}
		}
		
		return false;
	}

}
