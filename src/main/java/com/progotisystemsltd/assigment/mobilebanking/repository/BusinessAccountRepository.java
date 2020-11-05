package com.progotisystemsltd.assigment.mobilebanking.repository;

import com.progotisystemsltd.assigment.mobilebanking.model.BusinessAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessAccountRepository extends JpaRepository<BusinessAccount, Integer> {
    BusinessAccount findBusinessAccountByBankAccount_AccountNumber(Long accountNumber);

}
