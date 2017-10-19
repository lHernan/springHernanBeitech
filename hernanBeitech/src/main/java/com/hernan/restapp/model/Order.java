package com.hernan.restapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity order.
 *
 * @author Hernan
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7144767531108800295L;

	/** The order id. */
	private Long orderId;

	/** The delivery address. */
	private String deliveryAddress;

	/** The date. */
	private Date date;

	/** The order price. */
	private BigDecimal orderPrice;

	/** The customer. */
	private Customer customer;

	/** The order details. */
	private Set<OrderDetail> orderDetails;

	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId
	 *            the new order id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the delivery address.
	 *
	 * @return the delivery address
	 */
	@Column(name = "delivery_address", length = 255)
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * Sets the delivery address.
	 *
	 * @param deliveryAddress
	 *            the new delivery address
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the order price.
	 *
	 * @return the order price
	 */
	@Column(name = "price", precision = 16, scale = 2)
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	/**
	 * Sets the order price.
	 *
	 * @param orderPrice
	 *            the new order price
	 */
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer
	 *            the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the order details.
	 *
	 * @return the order details
	 */
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
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
	}

}
