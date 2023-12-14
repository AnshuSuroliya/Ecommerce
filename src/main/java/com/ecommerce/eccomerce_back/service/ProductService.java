package com.ecommerce.eccomerce_back.service;

import com.ecommerce.eccomerce_back.entity.Product;
import com.ecommerce.eccomerce_back.exception.ProductException;

public interface ProductService {
    public String createProduct(Product rq);
    public String deleteProduct(Long productid) throws ProductException;
    public Product updateProduct(Long productid,Integer quantity) throws ProductException;
    public Product findProductById(Long id) throws ProductException;
//    public Page<Product>getAllProduct(List<String>category,List<String>colors,String stock,Integer minPrice,Integer maxPrice,String sort);
}
