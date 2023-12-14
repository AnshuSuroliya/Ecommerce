package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
