package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
