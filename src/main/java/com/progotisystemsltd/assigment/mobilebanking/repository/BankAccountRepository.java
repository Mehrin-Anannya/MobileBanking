package com.progotisystemsltd.assigment.mobilebanking.repository;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findBankAccountByAccountNumber(Long accountNumber);
    BankAccount findBankAccountByMobilePhoneNumber(String mobilePhoneNumber);
}
