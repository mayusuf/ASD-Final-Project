package org.lotusbank.framework.ruleset;

import org.lotusbank.framework.domain.AccountEntry;

public class DepositPositiveAmountRule implements Rule<AccountEntry> {
    @Override
    public boolean isSatisfied(AccountEntry transaction) {
        if (transaction.getDescription().contains("deposit")) {
            return transaction.getAmount() > 0;
        }
        return true; // No rule for other transaction types
    }
}
