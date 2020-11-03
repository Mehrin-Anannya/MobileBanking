package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BusinessAccountInfo;
import com.progotisystemsltd.assigment.mobilebanking.repository.BusinessAccountInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessAccountInfoServiceImpl implements BusinessAccountInfoService {
    @Autowired
    private BusinessAccountInfoRepository businessAccountInfoRepository;

    @Override
    public void createBusinessAccount(BusinessAccountInfo businessAccountInfo) {
        businessAccountInfoRepository.save(businessAccountInfo);
    }

    @Override
    public BusinessAccountInfo getBusinessAccountInfoByAccountNumber(Long accountNumber) {
        return businessAccountInfoRepository.findBankAccountByAccountNumber(accountNumber);
    }
}
