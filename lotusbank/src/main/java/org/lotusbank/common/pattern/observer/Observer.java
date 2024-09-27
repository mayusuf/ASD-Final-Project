package org.lotusbank.common.pattern.observer;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;

public interface Observer {
    void update(Account account, AccountEntry accountEntry);
}
