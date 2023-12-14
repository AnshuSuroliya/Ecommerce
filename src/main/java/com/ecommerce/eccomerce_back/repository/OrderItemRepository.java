package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
