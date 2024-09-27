package org.lotusbank.ui.bank;
/*
		A basic implementation of the JDialog class.
*/

import org.lotusbank.banking.domain.BankAccount;
import org.lotusbank.banking.strategy.CheckingAccountStrategy;
import org.lotusbank.banking.strategy.SavingsAccountStrategy;
import org.lotusbank.common.Address;
import org.lotusbank.common.Customer;
import org.lotusbank.common.pattern.factory.BankingAccountFactory;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.ui.framework.JDialog_AddAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.*;

public class JDialog_AddPAcc extends JDialog_AddAccount {
    JLabel JLabel_Error;

    JTextField JTextField_BD;
    JTextField JTextField_EM;

    public JDialog_AddPAcc(BankForm parent) {
        super(parent);
        accountFactory = new BankingAccountFactory();
        // Additional initialization specific to JDialog_AddPAcc
        setTitle("Add personal account");
        setModal(true);

        JTextField_BD = new JTextField();
        JTextField_EM = new JTextField();
        getContentPane().add(JTextField_BD);
        JTextField_BD.setBounds(84,204,156,20);
        getContentPane().add(JTextField_EM);
        JTextField_EM.setBounds(84,228,156,20);

        JLabel6.setText("Birthdate");
        getContentPane().add(JLabel6);
        JLabel6.setForeground(Color.black);

        JLabel_Error = new JLabel();
        JLabel_Error.setForeground(Color.red);
        JLabel_Error.setVisible(false);
        getContentPane().add(JLabel_Error);
        JLabel_Error.setBounds(12,245,200,20);
    }

    @Override
    protected void initCommonComponents() {
        // Initialize common components specific to JDialog_AddPAcc

        //RADIO BUTTONS-------->
        JRadioButton_Chk.setText("Checkings");
        JRadioButton_Chk.setActionCommand("Checkings");
        getContentPane().add(JRadioButton_Chk);
        JRadioButton_Chk.setBounds(36,0,84,24);
        JRadioButton_Chk.setSelected(true);

        JRadioButton_Sav.setText("Savings");
        JRadioButton_Sav.setActionCommand("Savings");
        getContentPane().add(JRadioButton_Sav);
        JRadioButton_Sav.setBounds(36,24,84,24);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(JRadioButton_Chk);
        accountTypeGroup.add(JRadioButton_Sav);
        //------------------------->

        //Labels----------->
        JLabel1.setText("Name");
        getContentPane().add(JLabel1);
        JLabel1.setForeground(Color.black);
        JLabel1.setBounds(12,84,48,24);

        JLabel2.setText("Street");
        getContentPane().add(JLabel2);
        JLabel2.setForeground(Color.black);
        JLabel2.setBounds(12,108,48,24);

        JLabel3.setText("City");
        getContentPane().add(JLabel3);
        JLabel3.setForeground(Color.black);
        JLabel3.setBounds(12,132,48,24);

        JLabel4.setText("State");
        getContentPane().add(JLabel4);
        JLabel4.setForeground(Color.black);
        JLabel4.setBounds(12,156,48,24);

        JLabel5.setText("Zip");
        getContentPane().add(JLabel5);
        JLabel5.setForeground(Color.black);
        JLabel5.setBounds(12,180,48,24);

        JLabel6.setText("Birthdate");
        getContentPane().add(JLabel6);
        JLabel6.setForeground(Color.black);
        JLabel6.setBounds(12,204,96,24);

        JLabel7.setText("Email");
        getContentPane().add(JLabel7);
        JLabel7.setForeground(Color.black);
        JLabel7.setBounds(12,228,48,24);
        //Labels----------->

        //TextFields----------->
        getContentPane().add(JTextField_NAME);
        JTextField_NAME.setBounds(84,84,156,20);

        getContentPane().add(JTextField_CT);
        JTextField_CT.setBounds(84,132,156,20);

        getContentPane().add(JTextField_ST);
        JTextField_ST.setBounds(84,156,156,20);

        getContentPane().add(JTextField_STR);
        JTextField_STR.setBounds(84,108,156,20);

        getContentPane().add(JTextField_ZIP);
        JTextField_ZIP.setBounds(84,180,156,20);
        //----------->

        //Buttons ------->
        JButton_OK.setText("Add");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(48,264,84,24);
        JButton_Cancel.setText("Cancel");
        JButton_Cancel.setActionCommand("Cancel");
        getContentPane().add(JButton_Cancel);
        JButton_Cancel.setBounds(156,264,84,24);
        //--------------->

        //Account text field--------->
        getContentPane().add(JTextField_ACNR);
        JTextField_ACNR.setBounds(84,60,156,20);
        JLabel8.setText("Acc Nr");
        getContentPane().add(JLabel8);
        JLabel8.setForeground(Color.black);
        JLabel8.setBounds(12,60,48,24);
        //---------------->
    }

    @Override
    protected void JButtonOK_actionPerformed(ActionEvent event) {
        // Implement OK button action specific to JDialog_AddPAcc
        if(validateFields()) {
            JLabel_Error.setVisible(false);
            System.out.println("Add Button Pressed");
            addEntryInTable(createNewAccout());
            dispose();
        } else {
            JLabel_Error.setText("Please fill out all fields before submitting.");
            JLabel_Error.setVisible(true);
            revalidate();
            repaint();
        }
    }

    private boolean validateFields() {
        return !JTextField_ACNR.getText().trim().isEmpty() &&
                !JTextField_NAME.getText().trim().isEmpty() &&
                !JTextField_STR.getText().trim().isEmpty() &&
                !JTextField_CT.getText().trim().isEmpty() &&
                !JTextField_ST.getText().trim().isEmpty() &&
                !JTextField_ZIP.getText().trim().isEmpty() &&
                !JTextField_BD.getText().trim().isEmpty() &&
                !JTextField_EM.getText().trim().isEmpty();
    }

    private void addEntryInTable(Account account) {
        System.out.println("rowcount " + parentframe.getModel().getRowCount());

        parentframe.getRowData()[0] = account.getAccountNumber();
        parentframe.getRowData()[1] = account.getCustomer().getName();
        parentframe.getRowData()[2] = account.getCustomer().getAddress().getCity();
        parentframe.getRowData()[3] = account.getAccountType() == AccountType.COMPANY ? "C": "P";
        parentframe.getRowData()[4] = JRadioButton_Chk.isSelected()? "Ch": "S";
        parentframe.getRowData()[5] = String.valueOf(Objects.isNull(account.getBalance()) ? "0": account.getBalance());

        parentframe.getModel().addRow(parentframe.getRowData());
    }

    private Account createNewAccout() {
        Customer customer = new Customer(
                JTextField_NAME.getText(),
                JTextField_EM.getText(),
                JTextField_BD.getText());

        customer.setAddress(createNewAddress());
        String accountNumber = JTextField_ACNR.getText();

        BankAccount account = (BankAccount) accountFactory.createAccount(AccountType.PERSONAL, accountNumber, customer);

        if(JRadioButton_Chk.isSelected()){
            account.setStrategy(new CheckingAccountStrategy());
        } else {
            account.setStrategy(new SavingsAccountStrategy());
        }
        BankForm bankfrm = (BankForm) parentframe;
        bankfrm.getBankService().createAccount(account);
        return account;
    }

    private Address createNewAddress(){
        return new Address(JTextField_STR.getText(),JTextField_ST.getText(),JTextField_CT.getText(), JTextField_ZIP.getText());
    }
}