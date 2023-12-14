package com.ecommerce.eccomerce_back.request;

import com.ecommerce.eccomerce_back.entity.User;

public class AddCartItemRequest {
     private Long userId;
     private Long productId;
     private Integer quantity;
    public AddCartItemRequest(){

    }
    public AddCartItemRequest(Long userId, Long productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    //    private User user;
//    private AddItemRequest addItemRequest;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public AddItemRequest getAddItemRequest() {
//        return addItemRequest;
//    }
//
//    public void setAddItemRequest(AddItemRequest addItemRequest) {
//        this.addItemRequest = addItemRequest;
//    }
}
