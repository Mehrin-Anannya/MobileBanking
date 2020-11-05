package com.progotisystemsltd.assigment.mobilebanking.controller;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import com.progotisystemsltd.assigment.mobilebanking.model.BankAccountInfo;
import com.progotisystemsltd.assigment.mobilebanking.service.BankAccountServiceImpl;
import com.progotisystemsltd.assigment.mobilebanking.service.BusinessAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankAccountController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;

    @Autowired
    private BusinessAccountServiceImpl businessAccountInfoService;

    @GetMapping("/")
    public String viewIndexPage(Model model) {
        return "index";
    }

    @GetMapping("/viewAllBankAccounts")
    public String getAllBankAccountInfo(Model model) {
        model.addAttribute("viewAllBankAccounts", bankAccountService.getAllBankAccount());
        return "viewaccounts";
    }

    //set bank account as a model attribute to pre-populate the form
    @GetMapping(value = "/ceateAnAccountPage")
    public String showCreateAnAccountPage(Model model){
        model.addAttribute("banKAccountInfo", new BankAccountInfo());
        return "openanaccount";
    }

    @PostMapping(value = "/createAnAccount")
    public String createAnAccount(@ModelAttribute("banKAccountInfo") BankAccountInfo bankAccountInfo, Model model){
        String message;
        message = bankAccountService.createBankAccount(bankAccountInfo, model);
        model.addAttribute("message", message);
        model.addAttribute("banKAccountInfo", new BankAccountInfo());
        return "openanaccount";
    }

    @GetMapping(value = "/addMoneyPage")
    public String showAddMoneyPage(Model model){
        model.addAttribute("bankAccount", new BankAccount());
        return "addmoney";
    }

    @PostMapping(value = "/addMoney")
    public String addMoneyToBankAccount(@ModelAttribute("bankAccount")BankAccount bankAccount, Model model) throws Exception{
        String mobileNumber = bankAccount.getMobilePhoneNumber();
        bankAccount = bankAccountService.updateBankAccount(bankAccount);
        //When account doesn't exists
        if(bankAccount.getMobilePhoneNumber() == null)
            bankAccount.setMobilePhoneNumber(mobileNumber);
        model.addAttribute("bankAccount", bankAccount);
        return "addMoney";
    }

    @GetMapping(value = "/checkBalancePage")
    public String showCheckBalancePage(Model model){
        model.addAttribute("bankAccount", new BankAccount());
        return "checkbalance";
    }

    @PostMapping(value = "/checkBalance")
    public String getCheckBalanceByMPN(@ModelAttribute("bankAccount") BankAccount bankAccount, Model model){
        model.addAttribute("bankAccount", bankAccountService.checkBalanceWithMPN(bankAccount.getMobilePhoneNumber()));
        return "checkbalance";
    }

    @GetMapping(value = "/bankAccountId/{bankAccountId}")
    public BankAccount getBankAccountById(@PathVariable(value = "bankAccountId")Integer bankAccountId){
        return bankAccountService.getBankAccountById(bankAccountId);
    }
}
