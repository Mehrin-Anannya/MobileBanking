package com.progotisystemsltd.assigment.mobilebanking.controller;

import com.progotisystemsltd.assigment.mobilebanking.model.BusinessAccountInfo;
import com.progotisystemsltd.assigment.mobilebanking.service.BusinessAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/businessAccount/")
public class BusinessAccountInfoController {
    @Autowired
    private BusinessAccountInfoService businessAccountInfoService;

    @GetMapping(value = "/businessAccountNumber/{accountNumber}")
    public BusinessAccountInfo getBusinessAccountInfoByAccountNumber(@PathVariable(value = "accountNumber")Long accountNumber) {
        return businessAccountInfoService.getBusinessAccountInfoByAccountNumber(accountNumber);
    }
}

