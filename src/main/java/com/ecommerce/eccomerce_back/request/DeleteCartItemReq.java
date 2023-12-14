package com.ecommerce.eccomerce_back.request;

public class DeleteCartItemReq {
    private Long userId;
    private Long cartItemId;
    public DeleteCartItemReq(){

    }

    public DeleteCartItemReq(Long userId, Long cartItemId) {
        this.userId = userId;
        this.cartItemId = cartItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartId) {
        this.cartItemId = cartId;
    }
}
