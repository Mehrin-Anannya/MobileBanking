package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import com.progotisystemsltd.assigment.mobilebanking.model.BankAccountInfo;
import com.progotisystemsltd.assigment.mobilebanking.model.TransferMoney;
import org.springframework.ui.Model;

import java.util.List;

public interface BankAccountService {
  public String createBankAccount(BankAccountInfo bankAccountInfo, Model model);
  public List<BankAccount>  getAllBankAccount();
  public String getTransferMoney(TransferMoney transferMoney);
  public BankAccount checkBalanceWithMPN(String mobilePhoneNumber);
  public String updateBankAccount(BankAccount bankAccount);
  public String getWithdrawMoney(BankAccount drawMoney);
}
