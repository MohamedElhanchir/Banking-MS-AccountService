package elhanchir.mohamed.bankaccountservice.service;

import elhanchir.mohamed.bankaccountservice.dto.BankAccountRequestDTO;
import elhanchir.mohamed.bankaccountservice.dto.BankAccountResponseDTO;

public interface AccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
}
