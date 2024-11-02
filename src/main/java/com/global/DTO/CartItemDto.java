package com.global.DTO;

import com.global.models.Product;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CartItemDto {

	private int id;
	private int quantity;
	private Float unitPrice;
	private Float totalPrice;
	
	private ProductDto product;

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

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}
	
	
	
	
}
