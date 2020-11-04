package com.progotisystemsltd.assigment.mobilebanking.controller;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import com.progotisystemsltd.assigment.mobilebanking.service.BankAccountServiceImpl;
import com.progotisystemsltd.assigment.mobilebanking.service.BusinessAccountInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BankAccountController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;

    @Autowired
    private BusinessAccountInfoServiceImpl businessAccountInfoService;

    @GetMapping("/")
    public String viewIndexPage(Model model) {
        return "index";
    }

    @GetMapping("/viewAllBankAccounts")
    public String getAllBankAccountInfo(Model model) {
        model.addAttribute("viewAllBankAccounts", bankAccountService.getAllBankAccount());
        return "viewaccounts";
    }

    @GetMapping(value = "/bankAccountId/{bankAccountId}")
    public BankAccount getBankAccountById(@PathVariable(value = "bankAccountId")Integer bankAccountId){
        return bankAccountService.getBankAccountById(bankAccountId);
    }

    @GetMapping(value = "/bankAccountNumber/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable(value = "accountNumber")Long accountNumber){
        return bankAccountService.getBankAccountByAccountNumber(accountNumber);
    }

    @GetMapping(value = "/callCheckBalancePage")
    public String getCheckBalancePage(Model model){
        model.addAttribute("bankAccount", new BankAccount());
        return "checkbalance";
    }

    @PostMapping(value = "/checkBalance")
    public String getBankAccountByAccountNumber(@ModelAttribute("bankAccount") BankAccount bankAccount, Model model){
        model.addAttribute("bankAccount", bankAccountService.checkBalanceWithMPN(bankAccount.getMobilePhoneNumber()));
        return "checkbalance";
    }
}
