package org.lotusbank.framework.dataaccess;

import org.lotusbank.banking.domain.BankAccount;
import org.lotusbank.creditcard.domain.CreditCardAccount;

import java.util.List;

public interface DatabaseSubSystem {
    public void saveBankAccount(BankAccount account);
    public void updateBankAccount(BankAccount account);
    public BankAccount loadBankAccount(String acc);
    public List<BankAccount> getAllBankAccounts();

    public void saveCreditCardAccount(CreditCardAccount account);
    public void updateCreditCardAccount(CreditCardAccount account);
    public CreditCardAccount loadCreditCardAccount(String acc);
    public List<CreditCardAccount> getAllCreditCardAccounts();

}

