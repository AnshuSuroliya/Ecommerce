package com.ecommerce.eccomerce_back.service;

import com.ecommerce.eccomerce_back.entity.Address;
import com.ecommerce.eccomerce_back.entity.Order;
import com.ecommerce.eccomerce_back.entity.User;
import com.ecommerce.eccomerce_back.exception.OrderException;
import com.ecommerce.eccomerce_back.request.AddressRequest;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    public Order createOrder(Long userId,String streetAddress,String city,String zipCode,String state) throws OrderException;
    public List<Order>usersOrderHistory(Long userId) throws OrderException;
    public Order placedOrder(Long orderId) throws OrderException;
    public Order deliveredOrder(Long orderId) throws OrderException;
   // public Order cancelOrder(Long orderId) throws OrderException;
    public String deleteOrder(Long orderId) throws OrderException;
    public Order findOrderById(Long orderId) throws OrderException;


}
