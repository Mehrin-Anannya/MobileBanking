package com.progotisystemsltd.assigment.mobilebanking.model;

public class BankAccountInfo {
    private BankAccount bankAccount;
    private BusinessAccount businessAccountInfo;

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BusinessAccount getBusinessAccountInfo() {
        return businessAccountInfo;
    }

    public void setBusinessAccountInfo(BusinessAccount businessAccountInfo) {
        this.businessAccountInfo = businessAccountInfo;
    }
}
