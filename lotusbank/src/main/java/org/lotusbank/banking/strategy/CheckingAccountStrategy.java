package org.lotusbank.banking.strategy;

public class CheckingAccountStrategy implements BankingStrategy {
    @Override
    public double calculateInterest(double balance) {
        if (balance < 1000) return balance * .015;
        else return balance * .025;
    }

}
