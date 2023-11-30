package com.example.customerservice.web;

import com.example.customerservice.entitites.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

public class CustomerController {
    private CustomerRepository  customerRepository;

    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerRepository.findById(id).get();
    }
}
