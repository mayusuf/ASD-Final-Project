package org.lotusbank.framework.domain;

import lombok.Data;
import lombok.Getter;
import org.lotusbank.common.Customer;
import org.lotusbank.common.pattern.observer.Observer;
import org.lotusbank.common.pattern.observer.Subject;
import org.lotusbank.framework.repository.AccountRepository;
import org.lotusbank.framework.repository.AccountRepositoryImpl;
import org.lotusbank.framework.ruleset.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public abstract class Account implements Subject, Serializable {
    @Serial
    private static final long serialVersionUID = -9128140346995162779L;

    @Getter
    private int Id;

    private Customer customer;
    private String accountNumber;
    @Getter
    private List<AccountEntry> entryList = new ArrayList<>();
    private AccountType type;
    public SubType subType;

    private List<Observer> observers = new ArrayList<>();

    public AccountRepository accountRepository;
    public Account(String accountNumber, AccountType type) {
        this.accountNumber = accountNumber;
        this.type = type;

    }
    public abstract AccountType getAccountType();
    public abstract SubType getSubType();
    public abstract void addInterest();
    @Override
    public abstract void notifyObserver(AccountEntry accountEntry);
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public double getBalance() {
        double balance = 0;
        for (AccountEntry entry : entryList) {
            balance += entry.getAmount();
        }
        return balance;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit", getAccountNumber(), "");
        //entryList.add(entry);
        addEntry(entry);
        notifyObserver(entry);
    }

    public void withdraw(double amount, String description) {
        AccountEntry entry = new AccountEntry(-amount, description, getAccountNumber(), "");
        if (canWithdraw(entry)){
            //entryList.add(entry);
            addEntry(entry);
            notifyObserver(entry);
        }

    }
    private void addEntry(AccountEntry entry) {
        entryList.add(entry);
        if (accountRepository==null){
            accountRepository = new AccountRepositoryImpl();
        }
        accountRepository.addEntry(entry, this.getAccountNumber());
    }
    public void transferFunds(Account toAccount, double amount, String description) {
        AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());
        AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());

        entryList.add(fromEntry);

        toAccount.addEntry(toEntry);
    }

    private boolean canWithdraw(AccountEntry entry){

        // Define rules to apply
        Rule<AccountEntry> withdrawalRule = new WithdrawalLimitRule(this);
        Rule<AccountEntry> depositRule = new DepositPositiveAmountRule();
        Rule<AccountEntry> minimumBalanceRule = new MinimumBalanceRule(this);
        Rule<AccountEntry> fraudALertRule = new FraudAlertRule(this);

        // Create rule engine
        RuleEngine<AccountEntry> ruleEngine = new RuleEngine<>(Arrays.asList(withdrawalRule, depositRule, minimumBalanceRule, fraudALertRule));
        RuleEngine<AccountEntry> minimumBalanceRuleEngine = new RuleEngine<>(Arrays.asList(minimumBalanceRule));
        RuleEngine<AccountEntry> fraudAlertRuleEngine = new RuleEngine<>(Arrays.asList(fraudALertRule));

        // Evaluate rules before proceeding
        if (ruleEngine.evaluate(entry)) {
            System.out.println("Transaction successful. Remaining balance: " + getBalance());
            return true;
        } else if (!minimumBalanceRuleEngine.evaluate(entry)) {
            withdraw(10, "A penalty has been deducted as your balance fell below $400. Remaining balance: " + getBalance());
            return true;
        } else if (!fraudAlertRuleEngine.evaluate(entry)) {
            System.out.println("Fraud Alert!");
            return true;
        } else {
            System.out.println("Transaction failed due to rule violation.");
            return false;
        }
    }

}