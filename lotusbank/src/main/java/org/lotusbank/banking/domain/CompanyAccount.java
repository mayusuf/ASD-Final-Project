package org.lotusbank.banking.domain;

import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.framework.domain.SubType;

public class CompanyAccount extends BankAccount {
    public CompanyAccount(String accountNumber) {
        super(accountNumber, AccountType.COMPANY);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.COMPANY;
    }

    @Override
    public SubType getSubType() {
        return this.subType;
    }


}
