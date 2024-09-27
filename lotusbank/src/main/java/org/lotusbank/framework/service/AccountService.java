package org.lotusbank.framework.service;

import org.lotusbank.framework.domain.Account;

import java.util.Collection;

public interface AccountService {
    void createAccount(Account account);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount, String description);
}
