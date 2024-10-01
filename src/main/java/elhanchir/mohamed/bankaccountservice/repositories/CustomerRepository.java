package elhanchir.mohamed.bankaccountservice.repositories;

import elhanchir.mohamed.bankaccountservice.entities.BankAccount;
import elhanchir.mohamed.bankaccountservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
