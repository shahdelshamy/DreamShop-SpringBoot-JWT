/**
 * 
 */
package com.global.DTO;

import com.global.models.Order;
import com.global.models.Product;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * @author user
 *
 */
public class OrderItemDto {

	private int id;
	private int quantity;
	private float unitPrice;
	private int productId;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
	
	
	
	
	
	
}
