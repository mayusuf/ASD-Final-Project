package org.lotusbank.framework.dataaccess;

import org.lotusbank.framework.domain.Account;

import java.util.List;

public interface DatabaseFacade {
    void saveAccountToDatabase(Account account);
    void updateAccountToDatabase(Account account);
    Account loadAccountFromDatabase(String accountNumber);
    List<Account> getAccountsFromDatabase();
}
