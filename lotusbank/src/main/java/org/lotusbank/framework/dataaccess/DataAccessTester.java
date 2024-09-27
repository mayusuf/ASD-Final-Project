package org.lotusbank.framework.dataaccess;

import org.lotusbank.framework.dataaccess.entities.AccountEntity;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.repository.AccountDAO;

import java.util.List;


public class DataAccessTester {
    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();

        // Create a new student
        AccountEntity account1 = new AccountEntity();
        //accountDAO.saveAccount(account1);

        //List<AccountEntity> accounts = accountDAO.getAccounts();
        // Get a student by ID
        Account accountFromDB = accountDAO.getAccountByAccountNumber("13");
        System.out.println("Retrieved Account: " + accountFromDB.getAccountNumber());

        // Update a student
        //accountFromDB.setStatus("Modified");
        //accountDAO.updateAccount(accountFromDB);

        // Delete a student
        //accountDAO.deleteAccount(1);
    }
}

