package org.lotusbank.ui.bank;

import lombok.Getter;
import org.lotusbank.banking.domain.BankAccount;
import org.lotusbank.banking.service.BankingService;
import org.lotusbank.banking.service.BankingServiceImpl;
import org.lotusbank.framework.domain.Account;

import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.ui.framework.BaseUI;
import org.lotusbank.ui.framework.JDialog_Transaction;
import org.lotusbank.ui.framework.Launcher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class BankForm extends BaseUI {

    @Getter
    protected final BankingService bankService;

    JButton JButton_PersonalAccount = new JButton();
    JButton JButton_CompanyAccount = new JButton();
    JButton JButton_AddInterest = new JButton();
    JButton JButton_BillGen = new JButton();

    public BankForm(JFrame launcher) {
        super(launcher);
        bankService = BankingServiceImpl.getInstance();
        populateFrame();

        Collection<Account> accounts = bankService.getAllAccounts();
        populateTableData(accounts);
    }

    @Override
    protected void withdrawButtonAction(ActionEvent actionEvent) {
        super.withdrawButtonAction(actionEvent);
        Collection<Account> accounts = bankService.getAllAccounts();
        populateTableData(accounts);
    }

    @Override
    protected void depositButtonAction(ActionEvent actionEvent) {
        super.depositButtonAction(actionEvent);
        Collection<Account> accounts = bankService.getAllAccounts();
        populateTableData(accounts);
    }

    private void populateTableData(Collection<Account> accounts) {
        for (int i = 0; i < getModel().getRowCount(); ) {
            getModel().removeRow(0);
        }

        for (Account account: accounts){
            if (account.getAccountType()!=AccountType.CREDIT) {
                getRowData()[0] = account.getAccountNumber();
                getRowData()[1] = account.getCustomer().getName();
                getRowData()[2] = account.getCustomer().getAddress().getCity();
                getRowData()[3] = account.getAccountType() == AccountType.COMPANY ? "C" : "P";
                getRowData()[4] = "Ch";//TODO:"Ch": "S"
                getRowData()[5] = String.valueOf(Objects.isNull(account.getBalance()) ? "0" : account.getBalance());

                getModel().addRow(getRowData());
            }
        }

    }

    static public void main(String[] args) {
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            (new BankForm(new Launcher())).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

    @Override
    public void populateButtonActionListeners() {
        addButtonListener(JButton_Exit, this::exitButtonAction);
        addButtonListener(JButton_PersonalAccount, this::addPersonalAccountAction);
        addButtonListener(JButton_CompanyAccount, this::addCompanyAccountAction);
        addButtonListener(JButton_Deposit, this::depositButtonAction);
        addButtonListener(JButton_Withdraw, this::withdrawButtonAction);
        addButtonListener(JButton_AddInterest, this::addInterestButtonAction);

        addButtonListener(JButton_BillGen, this::generateBillAction);
    }

    private void addCompanyAccountAction(ActionEvent actionEvent) {
        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(this);
        pac.pack();
        pac.setSize(300, 350);
        pac.setLocationRelativeTo(getParent());
        pac.setVisible(true);

        if (newAccount) {
            // add row to table
            // Your implementation here
        }
    }

    private void addPersonalAccountAction(ActionEvent event) {
        JDialog_AddPAcc pac = new JDialog_AddPAcc(this);
        pac.pack();
        pac.setSize(300, 330);
        pac.setLocationRelativeTo(getParent());
        pac.setVisible(true);

        if (newAccount) {
            // add row to table
            // Your implementation here
        }
    }

    private void addInterestButtonAction(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(JButton_AddInterest, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);


        bankService.addInterest();
        int rowCount = model.getRowCount();
        int count = 0;

        Collection<Account> allAccounts = bankService.getAllAccounts();
        for (Account account : allAccounts) {
            if (account instanceof BankAccount bankAccount && count < rowCount) {
                model.setValueAt(bankAccount.getBalance(), count, 5);
                count++;
            }
        }
    }

    private void generateBillAction(ActionEvent actionEvent) {
        JDialog_GenerateBill generateBill = new JDialog_GenerateBill(this,bankService);
        generateBill.pack();
        generateBill.setSize(425, 350);
        generateBill.setLocationRelativeTo(getParent());
        generateBill.setVisible(true);
    }

    @Override
    public void populateButtons() {
        JButton_PersonalAccount.setText("Add personal account");
        JButton_PersonalAccount.setActionCommand("jbutton");
        JButton_PersonalAccount.setBounds(10, 20, 140, 33);
        JPanel1.add(JButton_PersonalAccount);

        JButton_CompanyAccount.setText("Add company account");
        JButton_CompanyAccount.setActionCommand("jbutton");
        JButton_CompanyAccount.setBounds(178, 20, 140, 33);
        JPanel1.add(JButton_CompanyAccount);

        JButton_BillGen.setText("Generate bill");
        JButton_BillGen.setActionCommand("jbutton");
        JButton_BillGen.setBounds(348, 20, 92, 33);
        JPanel1.add(JButton_BillGen);

        JButton_AddInterest.setText("Add interest");
        JButton_AddInterest.setBounds(468, 20, 106, 33);
        JPanel1.add(JButton_AddInterest);

        JButton_Deposit.setText("Deposit");
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JPanel1.add(JButton_Deposit);

        JButton_Withdraw.setText("Withdraw");
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JPanel1.add(JButton_Withdraw);

        JButton_Exit.setText("Back");
        JButton_Exit.setBounds(468, 248, 96, 31);
        JPanel1.add(JButton_Exit);
    }

    @Override
    public void populateTableColumns() {
        model.addColumn("AccountNr");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("P/C");
        model.addColumn("Ch/S");
        model.addColumn("Amount");
    }

    @Override
    public void setApplicationTitle() {
        setTitle("Bank Application.");
    }

    @Override
    protected JDialog_Transaction templateWithdrawMethod(String accnr) {

        JDialog_Transaction jdt = new JDialog_Withdraw(this, accnr);
        jdt.pack();
        jdt.setSize(300, 300);
        jdt.setLocationRelativeTo(getParent());

        return jdt;
    }

    @Override
    protected JDialog_Transaction templateDepositMethod(String accnr) {

        JDialog_Transaction jdt = new JDialog_Deposit(this, accnr);
        jdt.pack();
        jdt.setSize(300, 300);
        jdt.setLocationRelativeTo(getParent());

        return jdt;
    }
}
