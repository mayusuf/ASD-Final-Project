package org.lotusbank.framework.repository;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;

import java.util.Collection;

public interface AccountRepository {
    void saveAccount(Account account);
    void updateAccount(Account account);
    Account loadAccount(String accountNumber);
    Collection<Account> getAccounts();

    void addEntry(AccountEntry entry, String accountNumber);
}
