package com.global.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Cart;
import com.global.models.CartItem;
import com.global.models.Product;
import com.global.repositories.CartItemRepository;
import com.global.repositories.CartRepository;
import com.global.service.product.ProductService;

import ch.qos.logback.core.joran.conditional.IfAction;
import jakarta.transaction.Transactional;

@Service
public class CartItemService implements CartItemInterface {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;

	@Override
	public CartItem addCartItem(Integer cartId, Integer productId, int quantity) {
		
		//1.Get cart
		//2.Get product 
		//3.Check if the cart contain the product or not
		//4.If yes,increace the quantity
		//5.if no,create new cartItem
		
		Cart cart=cartService.getCart(cartId);
		Product product=productService.findById(productId);
		CartItem cartItem=cart.getItems().stream().filter(item->
					item.getProduct().getId()==(productId)
				).findFirst().orElse(null);
		
		if(cartItem == null) {
			System.out.println("cartId"+cartId.intValue()+" ProductId"+productId.intValue());
			cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setUnitPrice(product.getPrice());
		}else {
			cartItem.setQuantity(cartItem.getQuantity()+quantity);
		}
		cartItem.setTotalPrice();
		cart.addItem(cartItem);
		
	    // Save the cart (cascade will handle CartItem save as well if new)
		cartRepository.save(cart);
		
		return cartItem;
	}

	@Override
	public CartItem updateCartItem(Integer cartId, Integer productId, int quantity) {
		
		Cart cart=cartRepository.findById(cartId).get();
		Product product=productService.findById(productId);
		CartItem cartItem=cart.getItems().stream().filter(item->
					item.getProduct().getId()==productId
				).findFirst().orElseThrow(()->new ResourceNotFoundException("The Product Not Found"));

		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice();
		
		Float totalPriceInCart=(float) cart.getItems().stream().mapToDouble(item->item.getTotalPrice()).sum();
		
		cart.setTotalAmountPrice(totalPriceInCart);
		
		cartRepository.save(cart);
		
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem getCartItem(int cartId, int productId) {
		Cart cart=cartRepository.findById(cartId).orElseThrow(()->
			new ResourceNotFoundException("This Cart Not Found")
				);
		CartItem cartItem=cart.getItems().stream().filter(item->item.getProduct().getId()==productId)
				.findFirst().orElseThrow(()->
				new ResourceNotFoundException("This Product Not Found")
						);
		return cartItem;
	}

	@Override
	@Transactional
	public void removeCartItem(int cartId, int productId) {
		Cart cart=cartRepository.findById(cartId).orElseThrow(()->
		new ResourceNotFoundException("This Cart Not Found")
				);
		
		CartItem cartItemRemove =this.getCartItem(cartId, productId);
		
		
		cart.remove(cartItemRemove);

		cartRepository.save(cart);
		
		cartItemRepository.delete(cartItemRemove);;
		
	
	
	}
	
	

	
	
}
