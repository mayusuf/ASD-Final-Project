package org.lotusbank.banking.strategy;

public class SavingsAccountStrategy implements BankingStrategy {
    @Override
    public double calculateInterest(double balance) {
        if (balance < 1000) return balance * .01;
        else if (balance >= 1000 && balance < 5000) return balance * .02;
        else return balance * .04;
    }
}
