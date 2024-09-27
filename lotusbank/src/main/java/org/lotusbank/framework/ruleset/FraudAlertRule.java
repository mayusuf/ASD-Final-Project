package org.lotusbank.framework.ruleset;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;

public class FraudAlertRule implements Rule<AccountEntry> {
    private Account account;

    public FraudAlertRule(Account account) {
        this.account = account;
    }

    @Override
    public boolean isSatisfied(AccountEntry transaction) {
        if(transaction.getDescription().contains("withdrawal")) {
            return (transaction.getAmount() < getAverageWithdrawalAmount() * 2);
        }
        return true;
    }

    private double getAverageWithdrawalAmount() {
        double sum = 0;
        int count = 0;

        for (AccountEntry entry : account.getEntryList()) {
            if (entry.getDescription().toLowerCase().contains("withdrawal")) {
                sum += entry.getAmount();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return sum / count;
    }

}
