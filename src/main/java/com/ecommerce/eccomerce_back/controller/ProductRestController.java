package com.ecommerce.eccomerce_back.controller;

import com.ecommerce.eccomerce_back.repository.ProductRepository;
import com.ecommerce.eccomerce_back.entity.Product;
import com.ecommerce.eccomerce_back.exception.ProductException;
import com.ecommerce.eccomerce_back.request.DeleteProductRequest;
import com.ecommerce.eccomerce_back.request.SearchRequest;
import com.ecommerce.eccomerce_back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    @PreAuthorize("hasAnyAuthority('Admin','Seller')")
    public @ResponseBody String addProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @DeleteMapping("/deleteProduct")
    @PreAuthorize("hasAnyAuthority('Admin','Seller')")
    public @ResponseBody String deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) throws ProductException {
        return productService.deleteProduct(deleteProductRequest.getProductId());
    }
    @GetMapping("/findProduct/{productId}")
    public @ResponseBody Product findProductById(@PathVariable("productId") Long productId) throws ProductException {
        return productService.findProductById(productId);
    }
    @PutMapping("/updateProduct")
    @PreAuthorize("hasAnyAuthority('Admin','Seller')")
    public @ResponseBody Product updateProduct(@RequestBody Product product) throws ProductException {
        return productService.updateProduct(product.getId(),product.getQuantity());
    }
    @PostMapping("/search")
    public @ResponseBody List<Product> searchProduct(@RequestBody SearchRequest searchRequest){
        return productRepository.findByName(searchRequest.getSearchTerm());
    }
    @GetMapping("/products")
    public @ResponseBody Iterable<Product> find(){
        return productRepository.findAll();
    }
}

