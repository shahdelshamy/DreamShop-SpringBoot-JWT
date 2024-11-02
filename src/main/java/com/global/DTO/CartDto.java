package com.global.DTO;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.global.models.CartItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class CartDto {

	
	private int id;
	private Float totalAmountPrice;
	private Set<CartItemDto> items=new HashSet<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Float getTotalAmountPrice() {
		return totalAmountPrice;
	}
	public void setTotalAmountPrice(Float totalAmountPrice) {
		this.totalAmountPrice = totalAmountPrice;
	}
	public Set<CartItemDto> getItems() {
		return items;
	}
	public void setItems(Set<CartItemDto> items) {
		this.items = items;
	}
	
	
	
	
}
