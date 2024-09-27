package org.lotusbank.banking.service;


import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;
import org.lotusbank.framework.service.AccountService;
import org.lotusbank.framework.service.ReportService;

import java.util.List;
import java.util.Objects;

public class BankingReportServiceImpl implements ReportService {

    private final AccountService accountService;

    public BankingReportServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    public String generateReport() {

        StringBuilder report = new StringBuilder();

        accountService.getAllAccounts().forEach(account -> {
            report.append(generateReport(account));
            report.append("\n----------------------------------------------\n\n");
        });

        return report.toString();
    }


    public String generateReport(Account account) {
        StringBuilder report = new StringBuilder();
        report.append("Account Report\n");
        report.append("Account Number: ").append(account.getAccountNumber()).append("\n");
        report.append("Customer Name: ").append(account.getCustomer().getName()).append("\n");
        report.append("Balance: ").append(account.getBalance()).append("\n");
        report.append("Transactions:\n");
        report.append("Date\t\t\tType\t\tAmount\t\t\n");

        // Fetch all transactions for the account
        List<AccountEntry> transactions = account.getEntryList();

        for (AccountEntry transaction : transactions) {
            report.append(transaction.getDate()).append("\t\t")
                    .append(transaction.getDescription()).append(Objects.equals(transaction.getDescription(), "deposit") ? "\t\t" : "\t")
                    .append(transaction.getAmount()).append("\t\n");
//                    .append(transaction.getBalanceAfterTransaction()).append("\n");
        }

        return report.toString();
    }
}
