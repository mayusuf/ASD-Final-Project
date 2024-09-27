package org.lotusbank.common.pattern.factory;

import org.lotusbank.common.Customer;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountType;

public abstract class AccountFactory {
    public abstract Account createAccount(AccountType type, String acc, Customer customer);
}
