package com.progotisystemsltd.assigment.mobilebanking.model;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BUSINESS_ACCOUNT")
public class BusinessAccount implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUSINESS_ACCOUNT_ID")
    private Integer businessAccountId;

    @OneToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BANK_ACCOUNT_ID")
    BankAccount bankAccount;

    @Column(name = "TRADE_LICENSE_NUMBER")
    private Long tradeLicenseNumber;

    @Column(name = "TAX_IDENTIFICATION_NUMBER")
    private Long taxIdentificationNumber;

    public BusinessAccount() {

    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(Integer businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
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
