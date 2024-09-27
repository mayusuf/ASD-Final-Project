package org.lotusbank.framework.dataaccess;

import org.lotusbank.banking.domain.BankAccount;
import org.lotusbank.creditcard.domain.CreditCardAccount;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSubSystemImpl implements DatabaseSubSystem {

    public enum StorageType {BANK_ACCOUNT, CREDIT_ACCOUNT}

    public static final String OUTPUT_DIR = System.getProperty("user.dir")+ File.separator
            + "project" + File.separator
            + "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "com" + File.separator
            + "company" + File.separator
            + "framework" + File.separator
            + "facade" + File.separator
            + "persistentStorage";

    @Override
    public void saveBankAccount(BankAccount account) {
        List<BankAccount> accounts = getAllBankAccounts();
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        saveToStorage(StorageType.BANK_ACCOUNT, accounts);
    }

    @Override
    public void updateBankAccount(BankAccount account) {

    }

    @Override
    public BankAccount loadBankAccount(String acc) {
        return null;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return (List<BankAccount>) readFromStorage(StorageType.BANK_ACCOUNT);
    }

    @Override
    public void saveCreditCardAccount(CreditCardAccount account) {
        List<CreditCardAccount> accounts = getAllCreditCardAccounts();
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        saveToStorage(StorageType.CREDIT_ACCOUNT, accounts);
    }

    @Override
    public void updateCreditCardAccount(CreditCardAccount account) {

    }

    @Override
    public CreditCardAccount loadCreditCardAccount(String acc) {
        return null;
    }

    @Override
    public List<CreditCardAccount> getAllCreditCardAccounts() {
        return (List<CreditCardAccount>) readFromStorage(StorageType.CREDIT_ACCOUNT);
    }


    public static void saveToStorage(StorageType type, Object ob) {
        try{
//            FileOutputStream fileOut = new FileOutputStream("filename.ser", false);
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(myObject);
//            out.close();

            FileOutputStream fileOut = new FileOutputStream(OUTPUT_DIR+"\\"+type,false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ob);
            out.close();
            System.out.println("Objects written to file successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return retVal;
    }

}
