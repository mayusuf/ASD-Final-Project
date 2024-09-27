package org.lotusbank.common.pattern.factory;

import org.lotusbank.common.Customer;
import org.lotusbank.creditcard.domain.CreditCardAccount;
import org.lotusbank.framework.domain.AccountType;

public class CreditCardAccountFactory extends AccountFactory{
    @Override
    public CreditCardAccount createAccount(AccountType type, String acc, Customer customer) {
        CreditCardAccount creditCardAccount =  new CreditCardAccount(acc);
        creditCardAccount.setCustomer(customer);
        return creditCardAccount;
    }
}
