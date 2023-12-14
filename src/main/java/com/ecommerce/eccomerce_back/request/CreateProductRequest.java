package com.ecommerce.eccomerce_back.request;

import com.ecommerce.eccomerce_back.entity.Size;

import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {
//    private Long id;
private String title;
private String details;
private Integer price;
private Integer quantity;
private String brand;
private String color;
private String category;
private String imageUrl;
private Set<Size>sizes=new HashSet<>();

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
