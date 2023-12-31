package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    @Query("Select u From User u Where u.id=:userId")
    User findByUserId(@Param("userId") Long userId);
    @Query("Select u from User u where u.role='Seller' Or u.role='User'")
   List<User> findAllUsers();
}
