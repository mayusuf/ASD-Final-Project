package org.lotusbank.banking.domain;

import lombok.Setter;
import org.lotusbank.banking.strategy.BankingStrategy;
import org.lotusbank.common.pattern.observer.Observer;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;
import org.lotusbank.framework.domain.AccountType;

@Setter
public abstract class BankAccount extends Account {
    BankingStrategy strategy;
    public BankAccount(String accountNumber, AccountType type) {
        super(accountNumber, type);
    }

    public void addInterest() {
        double interest = this.strategy.calculateInterest(getBalance());
        super.deposit(interest);
    }
    @Override
    public void notifyObserver(AccountEntry entry) {
        for (Observer o : getObservers()) {
            o.update(this, entry);
        }
    }
}
