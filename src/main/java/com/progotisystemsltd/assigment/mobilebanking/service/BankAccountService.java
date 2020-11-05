package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import com.progotisystemsltd.assigment.mobilebanking.model.BankAccountInfo;
import org.springframework.ui.Model;

import java.util.List;

public interface BankAccountService {
  public String createBankAccount(BankAccountInfo bankAccountInfo, Model model);
  public List<BankAccount>  getAllBankAccount();
  public BankAccount getBankAccountById(Integer bankAccountId);
  public BankAccount getBankAccountByAccountNumber(Long accountNumber);
  public BankAccount checkBalanceWithMPN(String mobilePhoneNumber);
  public BankAccount updateBankAccount(BankAccount bankAccount);
}
