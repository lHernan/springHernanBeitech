package com.hernan.restapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity order detail.
 *
 * @author Hernan
 */
@Entity
@Table(name = "order_detail")

public class OrderDetail implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1318876462536097330L;

	/** The product. */
	private Product product;

	/** The order. */
	private Order order;

	/** The product quantity. */
	private Integer productQuantity;

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product
	 *            the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order
	 *            the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Gets the product quantity.
	 *
	 * @return the product quantity
	 */
	@Column(name = "product_quantity")
	public Integer getProductQuantity() {
		return productQuantity;
	}

	/**
	 * Sets the product quantity.
	 *
	 * @param productQuantity
	 *            the new product quantity
	 */
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

}
