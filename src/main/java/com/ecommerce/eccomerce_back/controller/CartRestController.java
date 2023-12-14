package com.ecommerce.eccomerce_back.controller;

import com.ecommerce.eccomerce_back.entity.CartItems;
import com.ecommerce.eccomerce_back.entity.User;
import com.ecommerce.eccomerce_back.exception.UserException;
import com.ecommerce.eccomerce_back.repository.CartItemRepository;
import com.ecommerce.eccomerce_back.repository.CartRepository;
import com.ecommerce.eccomerce_back.entity.Cart;
import com.ecommerce.eccomerce_back.exception.ProductException;
import com.ecommerce.eccomerce_back.repository.UserRepository;
import com.ecommerce.eccomerce_back.request.*;
import com.ecommerce.eccomerce_back.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartRestController {
@Autowired
    CartService cartService;
@Autowired
    CartRepository cartRepository;
@Autowired
    CartItemRepository cartItemRepository;
@Autowired
    UserRepository userRepository;
@PostMapping("/createcart")
    public @ResponseBody Cart createCart(@RequestBody CreateCartRequest createCartRequest){
    return cartService.createCart(createCartRequest.getEmail());
}
@PostMapping("/addcartitem")
    public @ResponseBody String addCartItem(@RequestBody AddCartItemRequest addCartItemRequest) throws ProductException {
    return cartService.addCartItem(addCartItemRequest.getUserId(),addCartItemRequest.getProductId(),addCartItemRequest.getQuantity());
}
@GetMapping("/findCartItem/{cartItemId}")
public @ResponseBody CartItems find(@PathVariable("cartItemId") Long cartItemId) throws UserException {
    return cartService.findCartItemById(cartItemId);
}
@DeleteMapping("/deleteCartItem")
    public @ResponseBody String deleteCartItem(@RequestBody DeleteCartItemReq deleteCartItemReq) throws UserException {
    return cartService.removeCartItem(deleteCartItemReq.getUserId(),deleteCartItemReq.getCartItemId());
}
@GetMapping("/displayCart")
    public @ResponseBody Iterable<CartItems> findall(){
    return cartItemRepository.findAll();
}
@PostMapping("/cartData")
    public @ResponseBody Cart findCart(@RequestBody CartRequest cartRequest){
    Cart cart=cartRepository.findByUserId(cartRequest.getUserId());
    System.out.println(cart);
    return cart;
}
@DeleteMapping("/deleteCart")
    public @ResponseBody String deleteCart(@RequestBody GetRequest getRequest){
    Cart cart=cartRepository.findByUserId(getRequest.getUserId());
    cartRepository.delete(cart);
    return "Cart deleted";
}

}
