package org.lotusbank.creditcard.service;


import org.lotusbank.creditcard.domain.CreditCardAccount;
import org.lotusbank.framework.domain.AccountEntry;
import org.lotusbank.framework.service.AccountService;
import org.lotusbank.framework.service.ReportService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class CreditReportServiceImpl implements ReportService {

    AccountService accountService;

    public CreditReportServiceImpl() {
        this.accountService = CreditCardAccountServiceImpl.getInstance();
    }

    @Override
    public String generateReport() {

        StringBuilder report = new StringBuilder();

        accountService.getAllAccounts().forEach(account -> {
            report.append(generateReport((CreditCardAccount) account));
            report.append(System.lineSeparator());
            report.append("----------------------------------------------");
            report.append(System.lineSeparator());
            report.append(System.lineSeparator());
        });

        return report.toString();
    }

    public String generateReport(CreditCardAccount account) {
        double totalCharges = 0, totalCredits = 0;
        Month thisMonth = LocalDate.now().getMonth();

        List<AccountEntry> lastMonthTransactions = account.getEntryList().stream()
                .filter(entry -> entry.getDate().getMonth().equals(thisMonth)).collect(Collectors.toList());

        for (AccountEntry e : lastMonthTransactions) {

            if (e.getDescription().equals("charge")) {
                totalCharges += e.getAmount();
            }
            if (e.getDescription().equals("deposit")) {
                totalCredits += e.getAmount();
            }

        }

        double previousBalance = account.getBalance() - totalCredits + totalCharges;
        double newBalance = previousBalance - totalCredits + totalCharges +
                account.getStrategy().monthlyInterest() * (previousBalance - totalCredits);

        double totalDue = account.minimumPayment() * newBalance;

        return account.getCustomer().getName() + ": "
                + account.getAccountType() + "-" + account.getAccountNumber() + System.lineSeparator() +
                "Last Month's balance : '" + previousBalance + "'" + System.lineSeparator() +
                "Total Charges for this Month : '" + totalCharges + "'" + System.lineSeparator() +
                "Total Credits for this Month : '" + totalCredits + "'" + System.lineSeparator() +
                "New balance : '" + newBalance + "'" + System.lineSeparator() +
                "Total due : '" + totalDue + "'";
    }
}
