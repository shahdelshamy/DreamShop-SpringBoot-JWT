package com.global.service.cart;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Cart;
import com.global.models.User;
import com.global.repositories.CartRepository;
import com.global.requests.UserRequest;

import jakarta.transaction.Transactional;


@Service
public class CartService implements CartInterface{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	private AtomicInteger uniqueNumber=new AtomicInteger(); 
	
	
	@Override
	public Cart getCart(int id) {
		Cart cart=cartRepository.findById(id).orElseThrow(()->
				new ResourceNotFoundException("This Cart Not Found")
				);
		return cart;
	}

	@Override
	public void deleteCart(int id) {
		try {
			cartRepository.deleteById(id);     //cascade when i delete the cart the items which in it remove
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("This Cart Not Found");
		}
	}
	
	
	@Override
	public void clearCart(int id) {
		try {
			
			Cart clearedCart=this.getCart(id);
			clearedCart.getItems().forEach((item)->{
				cartItemService.removeCartItem(id, item.getProduct().getId());
			});
			
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("This Cart Not Found");
		}
	}

	@Override
	public Float getTotalPrice(int id) {
		try {
			Cart cart =this.getCart(id);
			return cart.getTotalAmountPrice();
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("This Cart Not Found");
		}
	}


//	@Override
//	public int initializeCartId() {
//		Cart cart=new Cart();
//		cart.setId(uniqueNumber.getAndIncrement());
//		cartRepository.save(cart);
//		return cart.getId();
//	}
	
	
	@Override
	public Cart initializeCart(User user ) {
		Optional<Cart>cartOptional=  cartRepository.findByUserId(user.getId());
		if(cartOptional.isEmpty()) {
			Cart cart =new Cart();
			cart.setId(uniqueNumber.getAndIncrement());
			cart.setUser(user);
			return cartRepository.save(cart);
		}else {
			return cartOptional.get();
		}
	}
	

}
