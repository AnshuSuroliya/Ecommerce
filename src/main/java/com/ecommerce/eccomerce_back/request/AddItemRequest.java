package com.ecommerce.eccomerce_back.request;

public class AddItemRequest {
    private Long productId;
    private Integer quantity;
    public AddItemRequest(){

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
