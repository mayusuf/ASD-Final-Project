package org.lotusbank.creditcard.strategy;

public class SilverCardStrategy implements CreditCardStrategy {
    @Override
    public double monthlyInterest() {
        return .08;
    }

    @Override
    public double minimumPayment() {
        return .12;
    }

}
