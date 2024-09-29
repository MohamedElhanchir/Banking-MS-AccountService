package elhanchir.mohamed.bankaccountservice.web;

import elhanchir.mohamed.bankaccountservice.entities.BankAccount;
import elhanchir.mohamed.bankaccountservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@RestController
@RequestMapping("/api/bankAccounts")
@AllArgsConstructor
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;

    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getAccountById(@PathVariable String id) {
        Optional<BankAccount> account = bankAccountRepository.findById(id);
        return account.orElse(null);
    }

   @PostMapping
    public BankAccount createAccount(@RequestBody BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/{id}")
    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->
                new RuntimeException(String.format("Account with id %s not found", id)));
        if (bankAccount.getBalance() != null) {
            account.setBalance(bankAccount.getBalance());
        }
        if (bankAccount.getCurrency() != null) {
            account.setCurrency(bankAccount.getCurrency());
        }
        if (bankAccount.getType() != null) {
            account.setType(bankAccount.getType());
        }
        if (bankAccount.getCreationAt() != null) {
            account.setCreationAt(bankAccount.getCreationAt());
        }
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
