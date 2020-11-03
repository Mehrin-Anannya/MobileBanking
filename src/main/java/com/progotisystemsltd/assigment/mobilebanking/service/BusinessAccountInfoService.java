package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BusinessAccountInfo;

public interface BusinessAccountInfoService {
    public void createBusinessAccount(BusinessAccountInfo businessAccountInfo);
    public BusinessAccountInfo getBusinessAccountInfoByAccountNumber(Long accountNumber);
}
