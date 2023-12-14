package com.ecommerce.eccomerce_back.service;

import com.ecommerce.eccomerce_back.entity.Cart;
import com.ecommerce.eccomerce_back.entity.CartItems;
import com.ecommerce.eccomerce_back.entity.Product;
import com.ecommerce.eccomerce_back.entity.User;
import com.ecommerce.eccomerce_back.exception.ProductException;
import com.ecommerce.eccomerce_back.exception.UserException;
import com.ecommerce.eccomerce_back.request.AddItemRequest;

public interface CartService {
    public String addCartItem(Long userId,Long productId,Integer quantity) throws ProductException;
    public Cart findUserCart(Long userId);
    public String removeCartItem(Long userId,Long cartItemId) throws UserException;
    CartItems cartItemExist(Long userId, Product product, Cart cart);
    public CartItems findCartItemById(Long cartItemId) throws UserException;
    Cart createCart(String email);
}
