package elhanchir.mohamed.bankaccountservice;

import elhanchir.mohamed.bankaccountservice.entities.BankAccount;
import elhanchir.mohamed.bankaccountservice.entities.Customer;
import elhanchir.mohamed.bankaccountservice.enums.AccountType;
import elhanchir.mohamed.bankaccountservice.repositories.BankAccountRepository;
import elhanchir.mohamed.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        return args -> {

            Stream.of("Mohamed", "Ali", "Fatima", "Hassan").forEach(name -> {
                Customer customer = Customer.builder()
                        .name(name)
                        .build();
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {
                for (int i = 0; i < 10 ; i++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .balance(10000+Math.random()*90000)
                            .currency("MAD")
                            .type(Math.random()>0.5 ? AccountType.SAVING_ACCOUNT: AccountType.CURRENT_ACCOUNT)
                            .creationAt(new Date())
                            .customer(customer)
                            .build();
                    bankAccountRepository.save(bankAccount);
                }
            });


        };
    }

}
