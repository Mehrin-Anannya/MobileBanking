package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BusinessAccount;
import com.progotisystemsltd.assigment.mobilebanking.repository.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessAccountServiceImpl implements BusinessAccountService {
    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    @Override
    public void createBusinessAccount(BusinessAccount businessAccountInfo) {
        businessAccountRepository.save(businessAccountInfo);
    }

    @Override
    public BusinessAccount getBusinessAccountInfoByAccountNumber(Long accountNumber) {
        return businessAccountRepository.findBusinessAccountByBankAccount_AccountNumber(accountNumber);
    }
}
