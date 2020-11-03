package com.progotisystemsltd.assigment.mobilebanking.repository;

import com.progotisystemsltd.assigment.mobilebanking.model.BusinessAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessAccountInfoRepository extends JpaRepository<BusinessAccountInfo, Integer> {
    BusinessAccountInfo findBankAccountByAccountNumber(Long accountNumber);
}
