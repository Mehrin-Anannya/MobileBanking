package com.progotisystemsltd.assigment.mobilebanking.repository;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountNumberRepository extends JpaRepository<BankAccount, Long> {

}
