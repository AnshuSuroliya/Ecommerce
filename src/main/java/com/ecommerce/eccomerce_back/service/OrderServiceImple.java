package com.ecommerce.eccomerce_back.service;

import com.ecommerce.eccomerce_back.entity.*;
import com.ecommerce.eccomerce_back.repository.*;
import com.ecommerce.eccomerce_back.exception.OrderException;
import com.ecommerce.eccomerce_back.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

@Data
public class OrderServiceImple implements OrderService{
    private OrderRepository orderRepository;
    private CartService cartService;
    private UserRepository userRepository;
    private OrderItemRepository orderItemRepository;
    private AddressRepository addressRepository;

    public OrderServiceImple(OrderRepository orderRepository, CartService cartService, UserRepository userRepository, OrderItemRepository orderItemRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Order createOrder(Long userId,String streetAddress,String city,String zipCode,String state) throws OrderException{
        User user=userRepository.findByUserId(userId);
        Address address1=new Address();
        address1.setState(state);
        address1.setCity(city);
        address1.setZipCode(zipCode);
        address1.setStreetAddress(streetAddress);
        address1.setFirstName(user.getFirstName());
        address1.setLastName(user.getLastName());
        address1.setContactNumber(user.getContactNumber());
        address1.setAddress_user(user);
        Address address=addressRepository.save(address1);
//        List<Address> addresses  = user.getAddress();
//        if(addresses==null){
//            addresses =new ArrayList<Address>();
//        }
//        addresses.add(address1);
        user.getAddress().add(address1);
        userRepository.save(user);
        Cart cart=cartService.findUserCart(user.getId());
        List<OrderItem>orderItems=new ArrayList<>();
        for(CartItems items:cart.getCartItems()){
            OrderItem orderItem=new OrderItem();
            orderItem.setPrice(items.getPrice());
            orderItem.setProduct(items.getProduct());
            orderItem.setQuantity(items.getQuantity());
            orderItem.setUserId(items.getUserId());
            OrderItem createdItem=orderItemRepository.save(orderItem);
            orderItems.add(createdItem);
        }
        Order createOrder=new Order();
        createOrder.setUser(user);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        //createOrder.getPaymentDetails().setRazorpayPaymentStatus("Pending");
        createOrder.setAddress(address);
        createOrder.setOrderStatus("Pending");
        createOrder.setTotalItems(cart.getTotalItems());
        createOrder.setOrderDate(LocalDateTime.now());
        createOrder.setDeliveryDate(LocalDateTime.now().plusDays(5));
        Order saveOrder=orderRepository.save(createOrder);
        for(OrderItem item:orderItems){
            item.setOrder(saveOrder);
            orderItemRepository.save(item);
        }
        return saveOrder;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId)throws OrderException {

        List<Order> order=orderRepository.userOrders(userId);
        return order;

    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("Placed");
        //order.getPaymentDetails().setRazorpayPaymentStatus("Completed");
        return order;

    }
@Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order>order=orderRepository.findById(orderId);
        if(order.isPresent()){
          return order.get();
        }
        throw new OrderException("Order not found with id:"+orderId);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
    Order order=findOrderById(orderId);
    order.setOrderStatus("Delivered");
        return orderRepository.save(order);
    }



    @Override
    public String deleteOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        orderRepository.delete(order);
        return "Order Deleted";
    }
}
