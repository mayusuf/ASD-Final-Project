package org.lotusbank.creditcard.domain;


import lombok.Getter;
import lombok.Setter;
import org.lotusbank.common.pattern.observer.Observer;
import org.lotusbank.creditcard.strategy.CreditCardStrategy;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;
import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.framework.domain.SubType;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Setter
public class CreditCardAccount extends Account {

    @Getter
    private CreditCardStrategy strategy;

    @Getter
    String expiryDate;

    public CreditCardAccount(String accountNumber) {
        super(accountNumber, AccountType.CREDIT);
    }

    public void setCreditCardStrategy(CreditCardStrategy strategy) {
        this.strategy = strategy;
    }


    @Override
    public AccountType getAccountType() {
        return AccountType.CREDIT;
    }

    @Override
    public SubType getSubType() {
        return subType;
    }

    @Override
    public void addInterest() {

    }

    @Override
    public void notifyObserver(AccountEntry accountEntry) {
        if (accountEntry.getAmount() < -400)
            for (Observer observer : getObservers())
                observer.update(this, accountEntry);
    }

    private double previousBalance() {
        LocalDate current = LocalDate.now();
        LocalDate prevMonth = current.minusMonths(1);
        LocalDate startPrevMonth = prevMonth.with(firstDayOfMonth());
        LocalDate endPrevMonth = prevMonth.with(lastDayOfMonth());

        double sum = 0.0;
        for (AccountEntry entry : getEntryList()) {
            LocalDate entryDate = entry.getDate();
            if (entryDate.isAfter(startPrevMonth) && entryDate.isBefore(endPrevMonth)) {
                sum += entry.getAmount();
            }
        }
        return sum;
    }

    private double totalCharges() {
        LocalDate current = LocalDate.now();
        double sum = 0.0;
        for (AccountEntry entry : getEntryList()) {
            if (entry.getDescription().contains("Charge")
                    && entry.getDate().isAfter(current.with(firstDayOfMonth()))) {
                sum += entry.getAmount();
            }
        }
        return sum;
    }

    private double totalCredits() {
        LocalDate current = LocalDate.now();
        double sum = 0.0;
        for (AccountEntry entry : getEntryList()) {
            if (entry.getDescription().contains("deposit")
                    && entry.getDate().isAfter(current.with(firstDayOfMonth()))) {
                sum += entry.getAmount();
            }
        }
        return sum;
    }

    private double newBalance() {
        // 0 - 769 - 500
        //
        return previousBalance() -  totalCredits() + totalCharges() + minimumPayment() * (previousBalance() - totalCredits());
    }

    public double minimumPayment() {
        return strategy.minimumPayment();
    }

    private double totalDue() {
        return strategy.monthlyInterest() * newBalance();
    }

    public String billingReport() {
        return String.format("Name= $ %s\r\n", getCustomer().getName()) +
                String.format("Previous balance = $ %.2f\r\n", previousBalance()) +
                String.format("Total Credits = $ %.2f\r\n", totalCredits()) +
                String.format("Total Charges = $ %.2f\r\n", totalCharges()) +
                String.format("New balance = $ %.2f\r\n", newBalance()) +
                String.format("Total amount due = $ %.2f\r\n", totalDue());
    }
}
