package com.ecommerce.eccomerce_back.service;

import com.ecommerce.eccomerce_back.exception.UserException;
import com.ecommerce.eccomerce_back.repository.CartItemRepository;
import com.ecommerce.eccomerce_back.repository.CartRepository;
import com.ecommerce.eccomerce_back.entity.Cart;
import com.ecommerce.eccomerce_back.entity.CartItems;
import com.ecommerce.eccomerce_back.entity.Product;
import com.ecommerce.eccomerce_back.entity.User;
import com.ecommerce.eccomerce_back.exception.ProductException;
import com.ecommerce.eccomerce_back.repository.UserRepository;
import com.ecommerce.eccomerce_back.request.AddItemRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImple implements CartService {
    private CartRepository cartRepository;
    private ProductService productService;
    private UserRepository userRepository;
private CartItemRepository cartItemRepository;

    public CartServiceImple(CartRepository cartRepository,  ProductService productService, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart createCart(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null){
            return null;
        }
        System.out.println(user);
        Cart cart=new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId,Long productId,Integer quantity) throws ProductException {
        Cart cart=cartRepository.findByUserId(userId);
        System.out.println(cart);
        Product product=productService.findProductById(productId);
        CartItems exist=cartItemExist(userId,product,cart);
        if(exist==null) {
           // System.out.println(addItemRequest.getQuantity() +"   "+product.getPrice());
            CartItems cartItems = new CartItems();
            cartItems.setProduct(product);
            cartItems.setCart(cart);
            cartItems.setQuantity(1);
            product.setQuantity(product.getQuantity()-1);
            cartItems.setUserId(userId);
            cartItems.setPrice(product.getPrice()*cartItems.getQuantity());
            cartItemRepository.save(cartItems);
            cart.getCartItems().add(cartItems);

        }

        else {
            return "Items already present in cart";
        }
        Cart cart1=findUserCart(userId);
        cartRepository.save(cart1);
        return "Items added to cart successfully";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart=cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalItem=0;
        for(CartItems cartItems:cart.getCartItems()){
            totalPrice+=cartItems.getPrice();
            totalItem+=cartItems.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalItems(totalItem);
        return cartRepository.save(cart);
    }
    public String removeCartItem(Long userId, Long cartItemId) throws UserException {
        CartItems cartItems=findCartItemById(cartItemId);
        Optional<User> user1=userRepository.findById(cartItems.getUserId());
        Optional<User> user=userRepository.findById(userId);
        if(user1.equals(user)){
            cartItemRepository.deleteById(cartItemId);

            Cart cart=findUserCart(userId);
            cartRepository.save(cart);
            return "cart item removed";
        }
        else {
            throw new UserException("you can't remove item");
        }
    }
    public CartItems findCartItemById(Long cartItemId) throws UserException{

        Optional<CartItems>o=cartItemRepository.findById(cartItemId);
        if(o.isPresent()){
            return o.get();
        }
        throw new UserException("cart item not found with id:"+cartItemId);

    }
    public CartItems cartItemExist(Long userId, Product product, Cart cart) {
        CartItems cartItems=cartItemRepository.cartItemExist(userId,product,cart);
        return cartItems;

    }
}
