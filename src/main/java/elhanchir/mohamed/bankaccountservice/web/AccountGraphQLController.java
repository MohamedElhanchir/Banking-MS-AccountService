package elhanchir.mohamed.bankaccountservice.web;

import elhanchir.mohamed.bankaccountservice.dto.BankAccountRequestDTO;
import elhanchir.mohamed.bankaccountservice.dto.BankAccountResponseDTO;
import elhanchir.mohamed.bankaccountservice.entities.BankAccount;
import elhanchir.mohamed.bankaccountservice.entities.Customer;
import elhanchir.mohamed.bankaccountservice.repositories.BankAccountRepository;
import elhanchir.mohamed.bankaccountservice.repositories.CustomerRepository;
import elhanchir.mohamed.bankaccountservice.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccountGraphQLController {
    private AccountService accountService;
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;

    @QueryMapping
    List<BankAccount> accountsList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    BankAccount accountById(@Argument String id) {
        return bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException(String.format("Account with id %s not found", id)));
    }

    @MutationMapping
    BankAccountResponseDTO createAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id,bankAccount);
    }

    @MutationMapping
    Boolean deleteAccount(@Argument String id) {
        bankAccountRepository.deleteById(id);
        return true;
    }

    @QueryMapping
    List<Customer> customersList() {
        return customerRepository.findAll();
    }


}
