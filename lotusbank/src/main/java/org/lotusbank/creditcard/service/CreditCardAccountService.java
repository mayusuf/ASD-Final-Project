package org.lotusbank.creditcard.service;

import org.lotusbank.framework.service.AccountService;

public interface CreditCardAccountService extends AccountService {

    void charge(String accountNumber, double amount);
    String generateMonthlyBillingReport();
}
