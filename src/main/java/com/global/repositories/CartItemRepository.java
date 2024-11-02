package com.global.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	void deleteByCartIdAndProductId(int cartId, int productId);

}
