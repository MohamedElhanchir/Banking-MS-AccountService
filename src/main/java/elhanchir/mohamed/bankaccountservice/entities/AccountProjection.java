package elhanchir.mohamed.bankaccountservice.entities;


import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1", types = {BankAccount.class})
public interface AccountProjection {
    String getId();
    Double getBalance();
    String getType();
}
