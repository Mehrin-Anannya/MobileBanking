package com.progotisystemsltd.assigment.mobilebanking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BUSINESS_ACCOUNT_INFO")
public class BusinessAccountInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUSINESS_ACCOUNT_INFO_ID")
    private Integer businessAccountInfoId;
    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;
    @Column(name = "TRADE_LICENSE_NUMBER")
    private Long tradeLicenseNumber;
    @Column(name = "TAX_IDENTIFICATION_NUMBER")
    private Long taxIdentificationNumber;

    public Integer getBusinessAccountInfoId() {
        return businessAccountInfoId;
    }

    public void setBusinessAccountInfoId(Integer businessAccountInfoId) {
        this.businessAccountInfoId = businessAccountInfoId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getTradeLicenseNumber() {
        return tradeLicenseNumber;
    }

    public void setTradeLicenseNumber(Long tradeLicenseNumber) {
        this.tradeLicenseNumber = tradeLicenseNumber;
    }

    public Long getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(Long taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }
}
