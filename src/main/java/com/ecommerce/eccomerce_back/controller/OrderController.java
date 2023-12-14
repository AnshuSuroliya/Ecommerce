package com.ecommerce.eccomerce_back.controller;

import com.ecommerce.eccomerce_back.entity.Order;
import com.ecommerce.eccomerce_back.entity.User;
import com.ecommerce.eccomerce_back.exception.OrderException;
import com.ecommerce.eccomerce_back.repository.OrderRepository;
import com.ecommerce.eccomerce_back.request.CartRequest;
import com.ecommerce.eccomerce_back.request.CreateOrderRequest;
import com.ecommerce.eccomerce_back.service.OrderServiceImple;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
@Autowired
    OrderRepository orderRepository;
@Autowired
    OrderServiceImple orderServiceImple;
@PostMapping("/createOrder")
    public @ResponseBody Order createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws OrderException {
    return orderServiceImple.createOrder(createOrderRequest.getUserId(),createOrderRequest.getStreetAddress(),createOrderRequest.getCity(),createOrderRequest.getZipCode(),createOrderRequest.getState());
}
@DeleteMapping("/deleteOrder/{orderId}")
    public @ResponseBody String deleteOrder(@PathVariable("orderId") Long orderId) throws OrderException {
    return orderServiceImple.deleteOrder(orderId);
}
@PostMapping("/findUserOrders")
public @ResponseBody List<Order> find(@RequestBody CartRequest cartRequest)throws OrderException{
    return orderServiceImple.usersOrderHistory(cartRequest.getUserId());

}
@PostMapping("/showOrders")
public @ResponseBody List<Order> show(@RequestBody CartRequest cartRequest)throws OrderException{
    return orderRepository.showOrders(cartRequest.getUserId());
}
@PostMapping("/deliveredOrder/{orderId}")
@PreAuthorize("hasAnyAuthority('Admin','Seller')")
    public @ResponseBody Order deliverOrder(@PathVariable("orderId") Long orderId) throws OrderException {
    return orderServiceImple.deliveredOrder(orderId);
}
@GetMapping("/findAllOrders")
@PreAuthorize("hasAnyAuthority('Admin')")
    public @ResponseBody Iterable<Order> find(){
    return orderRepository.findAll();
}

}
