package com.ecommerce.eccomerce_back.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String details;
    private Integer  price;
    private String brand;
    private String color;
    private Integer quantity;
    private String category;
    private String imageUrl;
    @Embedded
    @ElementCollection
    @Column(name = "sizes")
    private Set<Size>sizes=new HashSet<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;
    public Product(){

    }

    public Product(long id, String title, String details, Integer price, String brand, String color, Integer quantity, String category, String imageUrl, Set<Size> sizes, List<OrderItem> orderItem) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.quantity = quantity;
        this.category = category;
        this.imageUrl = imageUrl;
        this.sizes = sizes;
        this.orderItem = orderItem;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
