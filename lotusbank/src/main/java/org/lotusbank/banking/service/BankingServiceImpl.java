package org.lotusbank.banking.service;


import org.lotusbank.common.Customer;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;
import org.lotusbank.framework.service.AccountServiceImpl;

public class BankingServiceImpl extends AccountServiceImpl implements BankingService{
    public static final String WITHDRAW = "withdraw";

    //Singleton Pattern used -------------->

    private static BankingServiceImpl instance = null;

    private BankingServiceImpl() {}
    public static BankingServiceImpl getInstance() {
        if (instance == null) {
            instance = new BankingServiceImpl();
        }
        return instance;
    }
    //------------------------------------->


    @Override
    public void withdraw(String accountNumber, Double amount) {
        super.withdraw(accountNumber,amount, WITHDRAW);
    }
    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
        Account fromAccount = accountRepository.loadAccount(fromAccountNumber);
        Account toAccount = accountRepository.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountRepository.updateAccount(fromAccount);
        accountRepository.updateAccount(toAccount);
    }

    public void addInterest() {
        for(Account account: getAllAccounts()){
            account.addInterest();
        }
    }

    public void generateAccountReports() {
        for (Account account : getAllAccounts()) {
            Customer customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountNumber());
            System.out.println("Account Holder: " + customer.getName());

            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");

            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n",
                        entry.getDate().toString(),
                        entry.getDescription(),
                        entry.getAmount());
            }

            System.out.println("----------------------------------------" + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }
    }


}
