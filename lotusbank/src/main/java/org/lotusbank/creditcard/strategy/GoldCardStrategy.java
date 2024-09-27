package org.lotusbank.creditcard.strategy;

public class GoldCardStrategy implements CreditCardStrategy {

    @Override
    public double monthlyInterest() {
        return .06;
    }
    @Override
    public double minimumPayment() {
        return .1;
    }
}
