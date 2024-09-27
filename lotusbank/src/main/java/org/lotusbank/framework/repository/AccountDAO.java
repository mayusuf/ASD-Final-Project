package org.lotusbank.framework.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.lotusbank.common.Customer;
import org.lotusbank.framework.dataaccess.HibernateFactory;
import org.lotusbank.framework.dataaccess.entities.AccountEntity;
import org.lotusbank.framework.dataaccess.entities.AccountEntryEntity;
import org.lotusbank.framework.dataaccess.entities.AddressEntity;
import org.lotusbank.framework.dataaccess.entities.CustomerEntity;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;
import org.lotusbank.framework.domain.AccountType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountDAO {

    public void insertIntoDatabase(Account account) {
        //accountlist.add(account); // add the new


        //AddCustomer
        //AddAddress
        AddressEntity address = LotusMapper.INSTANCE.
                getAddressEnity(account.getCustomer().getAddress());
        insertIntoDatabase(address);

        CustomerEntity customer = LotusMapper.INSTANCE
                .getCustomerEntity(account.getCustomer());
        customer.setAddress_id(address.getId());
        insertIntoDatabase(customer);

        //Add AccountEntity

        AccountEntity accountEntity = LotusMapper.INSTANCE.getAccountEnity(account);
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setCustomer_id(customer.getId());
        accountEntity.setAccountopeningdate(LocalDateTime.now().toString());
        accountEntity.setType(account.getType().toString());
        insertIntoDatabase(accountEntity);
    }

    /*

        public void updateAccount(Account account) {
            Account accountexist = loadAccount(account.getAccountNumber());
            if (accountexist != null) {
                accountlist.remove(accountexist); // remove the old
                accountlist.add(account); // add the new
            }

        }
    */
    public Collection<Account> getAccounts() {
        Collection<Account> accounts = new ArrayList<>();
        Session session = HibernateFactory.getSessionFactory().openSession();

        try {
            List<AccountEntity> dbAccounts = session.createQuery("from AccountEntity", AccountEntity.class)
                    .getResultList();

            for (AccountEntity dbAccount : dbAccounts){
                Account account = loadConcreteAccount(dbAccount);
                account.setAccountNumber(dbAccount.getAccountNumber());
                CustomerEntity customerEntity = session.get(
                        CustomerEntity.class, dbAccount.getCustomer_id());
                AddressEntity addressEntity = session.get(
                        AddressEntity.class, customerEntity.getAddress_id());

                Customer customer = LotusMapper.INSTANCE.getCustomer(customerEntity);
                customer.setAddress(LotusMapper.INSTANCE.getAddress(addressEntity));
                account.setCustomer(customer);

                populateAccountEntries(account, session);

                accounts.add(account);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();

        }
        return accounts;
    }

    private void populateAccountEntries(Account account, Session session) {

        Query<AccountEntryEntity> query = session.createQuery(
                "from AccountEntryEntity where account_id=:account_id",
                AccountEntryEntity.class
        );
        query.setParameter("account_id", account.getId());
        List<AccountEntryEntity> accountEntries = query.getResultList();

        ArrayList<AccountEntry> entries = new ArrayList<>();
        for (AccountEntryEntity entity: accountEntries){
            entries.add(LotusMapper.INSTANCE.getAccountEntry(entity));
        }
        account.setEntryList(entries);
    }

    private static Account loadConcreteAccount(AccountEntity dbAccount) {
        if (dbAccount.getType().equals(AccountType.PERSONAL.toString())){
            return LotusMapper.INSTANCE
                    .getPersonalAccount(dbAccount);
        } else if (dbAccount.getType().equals(AccountType.COMPANY.toString())){
            return LotusMapper.INSTANCE
                    .getCompanyAccount(dbAccount);
        } else if(dbAccount.getType().equals(AccountType.CREDIT.toString())) {
            return LotusMapper.INSTANCE
                    .getCreditCardAccount(dbAccount);
        }
        return LotusMapper.INSTANCE
                .getPersonalAccount(dbAccount);
    }

    public void addEntryToDB(AccountEntry entry, String accountNumber){
        AccountEntryEntity accountEntryEntity = LotusMapper.INSTANCE.
                getAccountEntryEntity(entry);
        accountEntryEntity.setEntry_date(LocalDate.now().toString());
        Account account = getAccountByAccountNumber(accountNumber);
        accountEntryEntity.setAccount_id(account.getId());
        insertIntoDatabase(accountEntryEntity);
    }

    //TODO: DB


    // Save accountEntity to the database
    public void insertIntoDatabase(Object accountEntity) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(accountEntity); // Hibernate save method
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Retrieve a student by ID
    public AccountEntity getAccount(int id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        AccountEntity accountEntity;
        try {
            accountEntity = session.get(AccountEntity.class, id); // Hibernate get method
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();

        }
        return accountEntity;
    }

    public Account getAccountByAccountNumber(String accNo) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        AccountEntity accountEntity;
        try {
            Query<AccountEntity> query = session.createQuery(
                    "from AccountEntity where accountNumber=:account_number",
                    AccountEntity.class
            );
            query.setParameter("account_number", accNo);
            accountEntity = query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();

        }
        Account account = LotusMapper.INSTANCE.getPersonalAccount(accountEntity);
        account.setId(accountEntity.getId());
        return account;

    }

    // Update student in the database
    public void updateAccount(AccountEntity student) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student); // Hibernate update method
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete student from the database
    public void deleteAccount(int id) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            AccountEntity accountEntity = session.get(AccountEntity.class, id);
            if (accountEntity != null) {
                session.delete(accountEntity); // Hibernate delete method
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}

