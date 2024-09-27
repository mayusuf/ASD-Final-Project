package org.lotusbank.framework.ruleset;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;

public class WithdrawalLimitRule implements Rule<AccountEntry> {
    private Account account;

    public WithdrawalLimitRule(Account account) {
        this.account = account;
    }

    @Override
    public boolean isSatisfied(AccountEntry transaction) {
        if (transaction.getDescription().contains("withdrawal")) {
            return account.getBalance() >= transaction.getAmount();
        }
        return true; // No rule for other transaction types
    }
}



