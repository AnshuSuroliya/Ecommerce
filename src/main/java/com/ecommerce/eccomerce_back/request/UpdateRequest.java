package com.ecommerce.eccomerce_back.request;

public class UpdateRequest {
    private String oldEmail;
    private String email;
    private String contactNumber;

    public UpdateRequest() {

    }

    public UpdateRequest(String oldEmail, String email, String contactNumber) {
        this.oldEmail = oldEmail;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}