package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("Select a From Order a Where a.user.id=:userId And (a.orderStatus='Placed' Or a.orderStatus='Delivered' Or a.orderStatus='Shipped' Or a.orderStatus='Pending') Order By a.orderDate Desc Limit 1")
 public List<Order> userOrders(@Param("userId") Long userId);
    @Query("Select a From Order a Where a.user.id=:userId And (a.orderStatus='Placed' Or a.orderStatus='Delivered' Or a.orderStatus='Shipped' Or a.orderStatus='Pending')")
   public  List<Order> showOrders(@Param("userId") Long userId);
}
