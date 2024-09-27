package org.lotusbank.banking.service;

import org.lotusbank.framework.service.AccountService;

public interface BankingService extends AccountService {

    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addInterest();
    void generateAccountReports();

    void withdraw(String accountNumber, Double amount);
}
