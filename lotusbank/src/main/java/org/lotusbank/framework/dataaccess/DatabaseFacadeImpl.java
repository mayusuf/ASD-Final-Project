package org.lotusbank.framework.dataaccess;

import org.lotusbank.banking.domain.BankAccount;
import org.lotusbank.creditcard.domain.CreditCardAccount;
import org.lotusbank.framework.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFacadeImpl implements DatabaseFacade {
    DatabaseSubSystem databaseSubSystem;

    public DatabaseFacadeImpl() {
        databaseSubSystem = new DatabaseSubSystemImpl();
    }

    @Override
    public void saveAccountToDatabase(Account account) {
        if (account instanceof BankAccount)
            databaseSubSystem.saveBankAccount((BankAccount) account);
        else
            databaseSubSystem.saveCreditCardAccount((CreditCardAccount) account);

    }

    @Override
    public void updateAccountToDatabase(Account account) {
        if (account instanceof BankAccount)
            databaseSubSystem.updateBankAccount((BankAccount) account);
        else
            databaseSubSystem.updateCreditCardAccount((CreditCardAccount) account);
    }

    @Override
    public Account loadAccountFromDatabase(String accountNumber) {
        Account account = databaseSubSystem.loadBankAccount(accountNumber);
        if (account != null)
            return account;
        return databaseSubSystem.loadCreditCardAccount(accountNumber);
    }

    @Override
    public List<Account> getAccountsFromDatabase() {
        List<BankAccount> bankAccounts = databaseSubSystem.getAllBankAccounts();
        List<CreditCardAccount> creditCardAccounts = databaseSubSystem.getAllCreditCardAccounts();
        List<Account> accounts = new ArrayList<>();
//        System.out.println(bankAccounts);
//        System.out.println(creditCardAccounts);

        if(bankAccounts!=null)
            accounts.addAll(bankAccounts);
        if(creditCardAccounts!=null)
            accounts.addAll(creditCardAccounts);

        return accounts;
    }
}
