package com.example.accountservice.web;


import com.example.accountservice.clients.CustomerRestClient;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.models.Customer;
import com.example.accountservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> accountList= bankAccountRepository.findAll();
        accountList.forEach(acc->{
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });
        return accountList;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccount(@PathVariable String id){
        BankAccount bankAccount= bankAccountRepository.findById(id).get();
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
