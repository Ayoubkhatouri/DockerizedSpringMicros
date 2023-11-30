package com.example.customerservice;

import com.example.customerservice.config.GlobalConfig;
import com.example.customerservice.entitites.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder()
							.firstName("Ayoub")
							.lastName("Khatouri")
							.email("ayoub@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.firstName("Ali")
					.lastName("Khatouri")
					.email("ali@gmail.com")
					.build());
		};
	}

}
