package com.ecommerce.eccomerce_back.repository;

import com.ecommerce.eccomerce_back.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
