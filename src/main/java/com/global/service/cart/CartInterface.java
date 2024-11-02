package com.global.service.cart;

import java.security.cert.Certificate;

import com.global.models.Cart;

public interface CartInterface {
	
	public Cart getCart(int id);
	
	public void clearCart(int id);
	
	public void deleteCart(int id);
	
	public Float getTotalPrice(int id);

	int initializeCartId();
	
	
	

}
