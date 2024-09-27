package org.lotusbank.banking.domain;

import org.lotusbank.common.pattern.observer.Observer;
import org.lotusbank.framework.domain.AccountEntry;
import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.framework.domain.SubType;

public class PersonalAccount extends BankAccount {

    public PersonalAccount(String accountNumber) {
        super(accountNumber, AccountType.PERSONAL);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.PERSONAL;
    }

    @Override
    public SubType getSubType() {
        return subType;
    }

    @Override
    public void notifyObserver(AccountEntry accountEntry) {
        if (shouldSendEmail(accountEntry)) {
            for (Observer o : getObservers()) {
                o.update(this, accountEntry);
            }
        }
    }

    private boolean shouldSendEmail(AccountEntry accountEntry) {
        return accountEntry.getAmount() > 500
                || accountEntry.getAmount() < -500
                || this.getBalance()  < 0;
    }
}
