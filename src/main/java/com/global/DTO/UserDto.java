package com.global.DTO;

import java.util.Set;

import com.global.models.Cart;
import com.global.models.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class UserDto {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	private CartDto cart;
	
	private Set<OrderDto> orders;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}

	public Set<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDto> orders) {
		this.orders = orders;
	}
	
	
	
	
	
	
}
