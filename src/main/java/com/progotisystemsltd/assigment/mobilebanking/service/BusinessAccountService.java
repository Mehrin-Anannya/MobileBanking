package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BusinessAccount;

public interface BusinessAccountService {
    public void createBusinessAccount(BusinessAccount businessAccountInfo);
    public BusinessAccount getBusinessAccountInfoByAccountNumber(Long accountNumber);
}
