package org.lotusbank.creditcard.strategy;

public interface CreditCardStrategy {
    double monthlyInterest();
    double minimumPayment();
}
