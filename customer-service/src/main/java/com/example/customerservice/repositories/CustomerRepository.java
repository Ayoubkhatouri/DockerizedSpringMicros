package com.example.customerservice.repositories;

import com.example.customerservice.entitites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
