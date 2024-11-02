package com.global.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Order;
import com.global.response.ApiResponse;
import com.global.service.order.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private static final HttpStatusCode Not_FOUND = null;
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/createOrder")
	public ResponseEntity<ApiResponse>createOrder(@RequestParam int userId){
		try {
			Order order=orderService.placeOrder(userId);
			return ResponseEntity.ok(new ApiResponse("Order Creating Sucessful",order));
		} catch (Exception e) {
			return ResponseEntity.status(Not_FOUND).body(new ApiResponse("Error",null));
		}
	}
	
	@GetMapping("/{orderId}/getOrder")
	public ResponseEntity<ApiResponse>getOrder(@PathVariable int orderId){
		try {
			Order order=orderService.getOrderById(orderId);
			return ResponseEntity.ok(new ApiResponse("Order Sucess",order));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@GetMapping("/{userId}/getOrders")
	public ResponseEntity<ApiResponse>getUserOrders(@PathVariable int userId){
		try {
			List<Order> orders=orderService.getUserOrders(userId);
			return ResponseEntity.ok(new ApiResponse("Order Sucess",orders));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
		}
	}

}
