package com.ecommerce.eccomerce_back.request;

public class DeleteProductRequest {
    private Long productId;
    public DeleteProductRequest(){

    }

    public DeleteProductRequest(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
