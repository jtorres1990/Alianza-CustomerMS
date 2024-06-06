package com.alianza.customer.jpa.repository;

import com.alianza.customer.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    List<CustomerEntity> findBySharedKey(String sharedKey);
}
