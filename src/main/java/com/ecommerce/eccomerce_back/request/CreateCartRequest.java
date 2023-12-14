package com.ecommerce.eccomerce_back.request;

import lombok.Data;

@Data
public class CreateCartRequest {
    private String email;
public CreateCartRequest(){

}
    public CreateCartRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
