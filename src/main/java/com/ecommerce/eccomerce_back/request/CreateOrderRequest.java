package com.ecommerce.eccomerce_back.request;

import com.ecommerce.eccomerce_back.entity.Address;
import com.ecommerce.eccomerce_back.entity.User;

public class CreateOrderRequest {
    private Long userId;
    private String streetAddress;
    private String city;
    private String zipCode;
    private String state;
    public CreateOrderRequest(){

    }
    public CreateOrderRequest(Long userId, String streetAddress, String city, String zipCode, String state) {
        this.userId = userId;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
