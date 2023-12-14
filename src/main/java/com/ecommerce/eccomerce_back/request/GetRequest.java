package com.ecommerce.eccomerce_back.request;

public class GetRequest {
    private Long userId;
    public GetRequest(){

    }

    public GetRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
