package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.Cart;
import com.ecommerce.eccomerce_back.entity.CartItems;
import com.ecommerce.eccomerce_back.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItems,Long> {
   @Query("SELECT i from CartItems i Where i.cart=:cart And i.product=:product And i.userId=:userId")
   CartItems cartItemExist(@Param("userId") Long userId,@Param("product") Product product,@Param("cart") Cart cart);
}
