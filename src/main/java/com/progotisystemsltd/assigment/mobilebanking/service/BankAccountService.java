package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;

import java.util.List;

public interface BankAccountService {
  public void createBankAccount(BankAccount bankAccount);
  public List<BankAccount>  getAllBankAccount();
  public BankAccount getBankAccountById(Integer bankAccountId);
  public BankAccount getBankAccountByAccountNumber(Long accountNumber);
  public BankAccount checkBalanceWithMPN(String mobilePhoneNumber);

}
