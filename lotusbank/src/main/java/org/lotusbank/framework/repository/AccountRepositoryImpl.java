package org.lotusbank.framework.repository;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;

import java.util.ArrayList;
import java.util.Collection;

public class AccountRepositoryImpl implements AccountRepository {
    Collection<Account> accountlist = new ArrayList<Account>();
    private AccountDAO accountDAO;

    public AccountRepositoryImpl() {
        accountDAO = new AccountDAO();
    }

    public void saveAccount(Account account) {
        accountlist.add(account); // add the new

        accountDAO.insertIntoDatabase(account);
    }

    public void updateAccount(Account account) {
        Account accountexist = loadAccount(account.getAccountNumber());
        if (accountexist != null) {
            accountlist.remove(accountexist); // remove the old
            accountlist.add(account); // add the new
        }

    }

    public Account loadAccount(String accountNumber) {
        /*for (Account account : accountlist) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }*/
        //return null;

        return accountDAO.getAccountByAccountNumber(accountNumber);
    }

    public Collection<Account> getAccounts() {
        return accountDAO.getAccounts();
        //return accountlist;
    }

    @Override
    public void addEntry(AccountEntry entry, String accountNumber) {
        accountDAO.addEntryToDB(entry, accountNumber);
    }

}
