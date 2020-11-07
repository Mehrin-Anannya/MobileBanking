package com.progotisystemsltd.assigment.mobilebanking.repository;

import com.progotisystemsltd.assigment.mobilebanking.model.AccountType;
import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    AccountType findAccountTypeByAccountTypeName(String accountTypeName);
}
