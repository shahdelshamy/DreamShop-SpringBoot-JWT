package com.global.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.global.enums.OrderStatus;
import com.global.models.OrderItem;
import com.global.models.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class OrderDto {

	private int id;
	private	Date oderDate;
	private float totalPrice;
	
	private OrderStatus orderStatus;
	
	private int userId;
	
	private Set<OrderItemDto>orderItems=new HashSet<>();

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOderDate() {
		return oderDate;
	}

	public void setOderDate(Date oderDate) {
		this.oderDate = oderDate;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Set<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}
	
	

	
	
	
}
