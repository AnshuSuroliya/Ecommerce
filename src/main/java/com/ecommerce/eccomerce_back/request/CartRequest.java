package com.ecommerce.eccomerce_back.request;

public class CartRequest {
    private Long userId;
    public CartRequest(){

    }
    public CartRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
