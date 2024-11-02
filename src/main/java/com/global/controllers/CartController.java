package com.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Cart;
import com.global.response.ApiResponse;
import com.global.service.cart.CartService;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	private static final HttpStatusCode NOT_FOUND = null;
	
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/{id}/getCart")
	public ResponseEntity<ApiResponse>getCart(@PathVariable int id){
		try {
			Cart cart=cartService.getCart(id);
			return ResponseEntity.ok(new ApiResponse("Success", cart));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/{id}/totalPrice")
	public ResponseEntity<ApiResponse>getTotalPrice(@PathVariable int id){
		try {
			Float totalPrice=cartService.getTotalPrice(id);
			return ResponseEntity.ok(new ApiResponse("The Total Price Is", totalPrice));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	
	@DeleteMapping("/{id}/deleteCart")
	public ResponseEntity<ApiResponse>deletCart(@PathVariable int id){
		try {
			cartService.deleteCart(id);
			return ResponseEntity.ok(new ApiResponse("delete Cart Success", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@DeleteMapping("/{id}/clearCart")
	public ResponseEntity<ApiResponse>clearCart(@PathVariable int id){
		try {
			cartService.clearCart(id);
			return ResponseEntity.ok(new ApiResponse("Clear Cart Success", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
		}
	}
	

}
