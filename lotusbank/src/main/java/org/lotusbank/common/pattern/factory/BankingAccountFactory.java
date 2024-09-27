package org.lotusbank.common.pattern.factory;

import org.lotusbank.banking.domain.BankAccount;
import org.lotusbank.banking.domain.CompanyAccount;
import org.lotusbank.banking.domain.PersonalAccount;
import org.lotusbank.common.Customer;
import org.lotusbank.framework.domain.AccountType;

public class BankingAccountFactory extends AccountFactory{
    @Override
    public BankAccount createAccount(AccountType type, String acc, Customer customer) {
        BankAccount account = null;

        switch (type) {
            case COMPANY:
                account = new CompanyAccount(acc);
                break;
            case PERSONAL:
                account = new PersonalAccount(acc);
                break;
            default:
                break;
        }

        account.setCustomer(customer);
        return account;
    }
}

