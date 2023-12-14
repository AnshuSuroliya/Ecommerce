package com.ecommerce.eccomerce_back.response;

public class LoginResponse {
   private String jwt;
    private String role;
    private Long userId;

    public LoginResponse(String jwt, String role, Long userId) {
        this.jwt = jwt;
        this.role = role;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
