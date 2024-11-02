package com.global.service.cart;

import com.global.models.CartItem;

public interface CartItemInterface {

	public CartItem addCartItem(Integer cartId,Integer productId,int quantity);
	
	public CartItem updateCartItem(Integer cartId,Integer productId,int quantity);
	
	public CartItem getCartItem(int cartId,int productId );
	
	public void removeCartItem(int cartId,int productId);
	
}
