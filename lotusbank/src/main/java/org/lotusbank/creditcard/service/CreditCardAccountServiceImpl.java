package org.lotusbank.creditcard.service;

import org.lotusbank.creditcard.domain.CreditCardAccount;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.framework.service.AccountServiceImpl;

public class CreditCardAccountServiceImpl extends AccountServiceImpl implements CreditCardAccountService {

    //Singleton Pattern used -------------->

    private static CreditCardAccountServiceImpl instance = null;
    private CreditCardAccountServiceImpl() {}
    public static CreditCardAccountServiceImpl getInstance() {
        if (instance == null) {
            instance = new CreditCardAccountServiceImpl();
        }
        return instance;
    }
    //------------------------------------->


    //Same as withdraw
    @Override
    public void charge(String accountNumber, double amount) {
        super.withdraw(accountNumber,amount,"charge");
    }

    @Override
    public String generateMonthlyBillingReport() {
        StringBuilder s = new StringBuilder();
        for (Account account : getAllAccounts()) {
            if (account.getAccountType() == AccountType.CREDIT){
                s.append(((CreditCardAccount) account).billingReport()).append("/n");
                System.out.println(((CreditCardAccount) account).billingReport());
            }
        }
        return s.toString();
    }

    public Account getAccountByName(String name) {
        for (Account account : getAllAccounts()) {
            if (account.getCustomer().getName().equals(name)){
                return account;
            }
        }
        return null;
    }
}
