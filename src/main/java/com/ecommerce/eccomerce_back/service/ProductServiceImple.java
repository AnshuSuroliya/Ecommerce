package com.ecommerce.eccomerce_back.service;

import com.ecommerce.eccomerce_back.repository.ProductRepository;
import com.ecommerce.eccomerce_back.entity.Product;
import com.ecommerce.eccomerce_back.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImple implements ProductService{
    private ProductRepository productRepository;
//    private UserService userService;
    public ProductServiceImple(ProductRepository productRepository){
        this.productRepository=productRepository;

    }
    @Override
    public String createProduct(Product rq) {
        Product product=new Product();
        product.setTitle(rq.getTitle());
        product.setDetails(rq.getDetails());
        product.setBrand(rq.getBrand());
        product.setColor(rq.getColor());
        product.setPrice(rq.getPrice());
        product.setQuantity(rq.getQuantity());
        product.setSizes(rq.getSizes());
        product.setImageUrl(rq.getImageUrl());
        product.setCategory(rq.getCategory());
        productRepository.save(product);
        return "Product added Successfully";

    }

    @Override
    public String deleteProduct(Long productid) throws ProductException {
        Product product=findProductById(productid);
    productRepository.delete(product);
        return "Product deleted";
    }

    @Override
    public Product updateProduct(Long productid,Integer quantity) throws ProductException {
    Product product=findProductById(productid);
    if(quantity!=0){
        product.setQuantity(quantity);
    }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product>o=productRepository.findById(id);
        if(o.isPresent()){
            return o.get();
        }
        throw new ProductException("Product not found with id:"+id);
    }

}
