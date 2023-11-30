package com.example.accountservice;

import com.example.accountservice.clients.CustomerRestClient;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.enums.AccountType;
import com.example.accountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.allCustomers().forEach(c->{
				bankAccountRepository.save(BankAccount.builder()
						.accountId(UUID.randomUUID().toString())
						.currency("MAD")
						.balance(Math.random()*80000)
						.createdAt(LocalDate.now())
						.type(AccountType.CURRENT_ACCOUNT)
						.CustomerId(c.getId())
						.build());

				bankAccountRepository.save(BankAccount.builder()
						.accountId(UUID.randomUUID().toString())
						.currency("MAD")
						.balance(Math.random()*55244)
						.createdAt(LocalDate.now())
						.type(AccountType.SAVING_ACDOUNT)
						.CustomerId(c.getId())
						.build());
			});

		};
	}
}
