package com.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.exceptions.ResourceNotFoundException;
import com.global.models.CartItem;
import com.global.response.ApiResponse;
import com.global.service.cart.CartItemService;
import com.global.service.cart.CartService;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addItem")
	public ResponseEntity<ApiResponse> addItemInCart(
			@RequestParam (required = false)  Integer  cartId,
			@RequestParam Integer productId,
			@RequestParam int quantity
			){
		try {
			if(cartId == null) {
				cartId=cartService.initializeCartId();
			}
			CartItem item= cartItemService.addCartItem(cartId, productId, quantity);
			return ResponseEntity.ok(new ApiResponse("Success",item)) ;
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse>updateItem(
			@RequestParam int cartId,
			@RequestParam int productId,
			@RequestParam int quantity
			){
		try {
			CartItem item= cartItemService.updateCartItem(cartId, productId, quantity);
			return ResponseEntity.ok(new ApiResponse("Success",item)) ;
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/getCartItem")
	public ResponseEntity<ApiResponse>getItem(
			@RequestParam int cartId,
			@RequestParam int productId
			){
		try {
			CartItem item= cartItemService.getCartItem(cartId, productId);
			return ResponseEntity.ok(new ApiResponse("Success",item)) ;
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	
	@DeleteMapping("/deleteCartItem")
	public ResponseEntity<ApiResponse>deleteItem(
			@RequestParam int cartId,
			@RequestParam int productId
			){
		try {
		 cartItemService.removeCartItem(cartId, productId);
			return ResponseEntity.ok(new ApiResponse("Deleting Success",null)) ;
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	
	
	
	
}
