package com.ecommerce.eccomerce_back.request;

public class PaymentRequest {
    private Long orderId;
    public PaymentRequest(){

    }
    public PaymentRequest(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
