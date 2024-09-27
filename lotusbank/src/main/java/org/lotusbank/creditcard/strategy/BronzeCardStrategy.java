package org.lotusbank.creditcard.strategy;

public class BronzeCardStrategy implements CreditCardStrategy {


    @Override
    public double monthlyInterest() {
        return .1;
    }

    @Override
    public double minimumPayment() {
        return .14;
    }
}
