package com.example.accountservice.clients;

import com.example.accountservice.models.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustomer") //if customer service is down
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultAllCustomers")
    List<Customer> allCustomers();

    default Customer getDefaultCustomer(Long id,Exception exception){
        Customer customer=new Customer();
        customer.setId(id);
        customer.setFirstName("Not available");
        customer.setLastName("Not available");
        customer.setEmail("Not available");
        return customer;
    }

    default List<Customer> getDefaultAllCustomers(Exception exception){
        return List.of();
    }
}
