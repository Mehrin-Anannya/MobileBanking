package com.progotisystemsltd.assigment.mobilebanking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_TYPE_ID")
    private Integer accountTypeId;
    @Column(name = "ACCOUNT_TYPE_NAME")
    private String accountTypeName;

    public AccountType() {
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }
}
