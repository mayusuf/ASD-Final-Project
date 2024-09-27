package org.lotusbank.framework.ruleset;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;

public class MinimumBalanceRule implements Rule<AccountEntry>{
    private Account account;

    public MinimumBalanceRule(Account account) {
        this.account = account;
    }

    @Override
    public boolean isSatisfied(AccountEntry transaction) {
        if(transaction.getDescription().contains("withdrawal")) {
            return (account.getBalance() - transaction.getAmount()) >= 400;
        }

        return true;
    }
}
