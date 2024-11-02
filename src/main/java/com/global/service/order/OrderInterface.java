package com.global.service.order;

import java.util.List;

import com.global.models.Order;

public interface OrderInterface {
	
	Order placeOrder(int userId);
	Order getOrderById(int orderId);
	List<Order>getUserOrders(int userId);

}
