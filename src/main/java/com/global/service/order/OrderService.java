package com.global.service.order;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.DTO.UserDto;
import com.global.enums.OrderStatus;
import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Cart;
import com.global.models.CartItem;
import com.global.models.Order;
import com.global.models.OrderItem;
import com.global.models.Product;
import com.global.models.User;
import com.global.repositories.CartRepository;
import com.global.repositories.OrderRepository;
import com.global.repositories.ProductRepository;
import com.global.service.cart.CartService;
import com.global.service.user.UserService;

@Service
public class OrderService implements OrderInterface{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	@Override
	public Order placeOrder(int userId) {
		Cart cart=cartRepository.findByUserId(userId);
		Order order=createOrder(cart);
		List<OrderItem>orderItems=getOrderItem(order,cart);
		order.setOrderItems(new HashSet<>(orderItems));
		order.setTotalPrice(cart.getTotalAmountPrice());
		Order orderSaved=orderRepository.save(order);
		cartService.clearCart(cart.getId());
		return orderSaved;
	}

	private List<OrderItem> getOrderItem(Order order, Cart cart) {
		return cart.getItems().stream().map(item->
		{
			Product product=item.getProduct();
			product.setQuantity(product.getQuantity()-item.getQuantity());
			productRepository.save(product);
			return new OrderItem(
					order,
					product,
					item.getQuantity(),
					item.getUnitPrice()
					);
		}
				).toList();
		
	}

	private Order createOrder(Cart cart) {
		Order order=new Order();
		order.setOrderStatus(OrderStatus.PINDING);
		order.setOderDate(new Date());
		return order;
	}

	@Override
	public Order getOrderById(int orderId) {
		return orderRepository.findById(orderId).orElseThrow(()->
		new ResourceNotFoundException("Order Not Found")
				);
	}

	@Override
	public List<Order> getUserOrders(int userId) {
		UserDto user=userService.getUserById(userId);
		return orderRepository.findByUserId(userId);
	}

}
