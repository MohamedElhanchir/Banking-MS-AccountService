package elhanchir.mohamed.bankaccountservice.service;

import elhanchir.mohamed.bankaccountservice.dto.BankAccountRequestDTO;
import elhanchir.mohamed.bankaccountservice.dto.BankAccountResponseDTO;
import elhanchir.mohamed.bankaccountservice.entities.BankAccount;
import elhanchir.mohamed.bankaccountservice.mappers.AccountMapper;
import elhanchir.mohamed.bankaccountservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private  BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .balance(bankAccountRequestDTO.getBalance())
                .currency(bankAccountRequestDTO.getCurrency())
                .type(bankAccountRequestDTO.getType())
                .creationAt(new Date())
                .build();

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
       /* BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(savedBankAccount.getId())
                .balance(savedBankAccount.getBalance())
                .currency(savedBankAccount.getCurrency())
                .type(savedBankAccount.getType())
                .creationAt(savedBankAccount.getCreationAt())
                .build();*/
        return accountMapper.fromBankAccount(savedBankAccount);
    }
}
