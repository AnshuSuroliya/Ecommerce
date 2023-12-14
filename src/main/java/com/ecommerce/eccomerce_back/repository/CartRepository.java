package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT c from Cart c Where c.user.id=:userId")
    public Cart findByUserId(@Param("userId")Long userId);
}
