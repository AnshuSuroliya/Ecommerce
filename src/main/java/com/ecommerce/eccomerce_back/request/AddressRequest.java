package com.ecommerce.eccomerce_back.request;

public class AddressRequest {
    private String streetAddress;
    private String city;
    private String zipCode;
    private String state;

    public AddressRequest(String streetAddress, String city, String zipCode, String state) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
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
