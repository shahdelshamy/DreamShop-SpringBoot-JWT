package com.global.models;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Float totalAmountPrice;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
	private Set<CartItem> items=new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	

	public Float getTotalAmountPrice() {
		return totalAmountPrice;
	}

	public void setTotalAmountPrice(Float totalAmountPrice) {
		this.totalAmountPrice = totalAmountPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Set<CartItem> getItems() {
		return items;
	}

	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	
	public void updateTotalAmountPrice() {
		
//		items.stream().map((item)->
//		this.totalAmountPrice+=item.getQuantity()*item.getUnitPrice()			
//				);
		
		this.totalAmountPrice=(float)items.stream().mapToDouble(item->
		item.getQuantity()*item.getUnitPrice()
				).sum();
				
		
	}

	public void addItem(CartItem cartItem) {
		items.add(cartItem);
		cartItem.setCart(this);
		this.updateTotalAmountPrice();
	}

	public void remove(CartItem cartItemRemove) {
		items.remove(cartItemRemove);
		cartItemRemove.setCart(null);
		this.updateTotalAmountPrice();
		
	}
	
	
	
}
