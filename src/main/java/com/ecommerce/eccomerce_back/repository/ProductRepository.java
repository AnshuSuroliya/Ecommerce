package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("Select p From Product p Where p.title LIke %:term%")
    List<Product> findByName(@Param("term") String term);
//    @Query("SELECT p FROM Product p"+"WHERE ")
//    public List<Product> filterProducts(@Param("minPrice") Integer minPrice,@Param("maxPrice") Integer maxPrice,@Param("sort") String sort);
}
