package org.lotusbank.ui.ccard;
/*
		A basic implementation of the JDialog class.
*/

import org.lotusbank.common.Address;
import org.lotusbank.common.Customer;
import org.lotusbank.common.pattern.factory.CreditCardAccountFactory;
import org.lotusbank.creditcard.domain.CreditCardAccount;
import org.lotusbank.creditcard.strategy.BronzeCardStrategy;
import org.lotusbank.creditcard.strategy.GoldCardStrategy;
import org.lotusbank.creditcard.strategy.SilverCardStrategy;
import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.ui.framework.BaseUI;
import org.lotusbank.ui.framework.JDialog_AddAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class JDialog_AddCreditCardAccount extends JDialog_AddAccount {
    JLabel JLabel_Error;

    JTextField JTextField_CCNR;
    JTextField JTextField_ExpDate;

    public JDialog_AddCreditCardAccount(BaseUI parent) {
        super(parent);
        accountFactory = new CreditCardAccountFactory();
        // Additional initialization specific to JDialog_AddCCAccount
        setTitle("Add credit card account");
        setModal(true);

        JTextField_CCNR = new JTextField();
        JTextField_ExpDate = new JTextField();


        getContentPane().add(JTextField_CCNR);
        JTextField_CCNR.setBounds(84,252,156,20);
        getContentPane().add(JTextField_ExpDate);
        JTextField_ExpDate.setBounds(84,276,156,20);

        JLabel_Error = new JLabel();
        JLabel_Error.setForeground(Color.red);
        JLabel_Error.setVisible(false);
        getContentPane().add(JLabel_Error);
        JLabel_Error.setBounds(12,293,200,20);
    }

    @Override
    protected void initCommonComponents() {
        // Initialize common components specific to JDialog_AddCCAccount

        //JRadioButton_Gold
        JRadioButton_Chk.setText("Gold");
        JRadioButton_Chk.setActionCommand("Checkings");
        getContentPane().add(JRadioButton_Chk);
        JRadioButton_Chk.setBounds(36,12,84,24);
        JRadioButton_Chk.setSelected(true);
        //-------------->

        //JRadioButton_Silver
        JRadioButton_Sav.setText("Silver");
        JRadioButton_Sav.setActionCommand("Savings");
        getContentPane().add(JRadioButton_Sav);
        JRadioButton_Sav.setBounds(36,36,84,24);
        //-------------->

        //Radio Button---->
        JRadioButton_Extra = new JRadioButton();
        JRadioButton_Extra.setText("Bronze");
        JRadioButton_Extra.setActionCommand("Savings");
        getContentPane().add(JRadioButton_Extra);
        JRadioButton_Extra.setBounds(36,60,84,24);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(JRadioButton_Chk);
        accountTypeGroup.add(JRadioButton_Sav);
        accountTypeGroup.add(JRadioButton_Extra);

        //Labels------->
        JLabel1.setText("Name");
        getContentPane().add(JLabel1);
        JLabel1.setForeground(java.awt.Color.black);
        JLabel1.setBounds(12,108,48,24);

        JLabel2.setText("Street");
        getContentPane().add(JLabel2);
        JLabel2.setForeground(java.awt.Color.black);
        JLabel2.setBounds(12,132,48,24);

        JLabel3.setText("City");
        getContentPane().add(JLabel3);
        JLabel3.setForeground(java.awt.Color.black);
        JLabel3.setBounds(12,156,48,24);

        JLabel4.setText("State");
        getContentPane().add(JLabel4);
        JLabel4.setForeground(java.awt.Color.black);
        JLabel4.setBounds(12,180,48,24);

        JLabel5.setText("Zip");
        getContentPane().add(JLabel5);
        JLabel5.setForeground(java.awt.Color.black);
        JLabel5.setBounds(12,204,48,24);

        JLabel6.setText("CC number");
        getContentPane().add(JLabel6);
        JLabel6.setForeground(java.awt.Color.black);
        JLabel6.setBounds(12,252,96,24);

        JLabel7.setText("Exp. Date");
        getContentPane().add(JLabel7);
        JLabel7.setForeground(java.awt.Color.black);
        JLabel7.setBounds(12,276,72,24);


        //Jtext fields ------->
        getContentPane().add(JTextField_NAME);
        JTextField_NAME.setBounds(84,108,156,20);

        getContentPane().add(JTextField_CT);
        JTextField_CT.setBounds(84,156,156,20);

        getContentPane().add(JTextField_ST);
        JTextField_ST.setBounds(84,180,156,20);

        getContentPane().add(JTextField_STR);
        JTextField_STR.setBounds(84,132,156,20);

        getContentPane().add(JTextField_ZIP);
        JTextField_ZIP.setBounds(84,204,156,20);


        //Button----------->
        JButton_OK.setText("Add");
        JButton_OK.setActionCommand("OK");
        JButton_OK.setBounds(48,312,84,24);

        JButton_Cancel.setText("Cancel");
        JButton_Cancel.setActionCommand("Cancel");
        JButton_Cancel.setBounds(156,312,84,24);
        //-------->


        //------The confusing one---->
        JLabel8.setText("Email");
        getContentPane().add(JLabel8);
        JLabel8.setForeground(java.awt.Color.black);
        JLabel8.setBounds(12,228,48,24);
        getContentPane().add(JTextField_ACNR);
        JTextField_ACNR.setBounds(84,228,156,20);
    }

    @Override
    protected void JButtonOK_actionPerformed(ActionEvent event) {
        // Implement OK button action specific to JDialog_AddCCAccount
        if (validateFields()) {
            JLabel_Error.setVisible(false);
            addEntryInTable(createNewAccount());
            dispose();
        } else {
            JLabel_Error.setText("Please fill out all fields before submitting.");
            JLabel_Error.setVisible(true);
        }
    }

    private boolean validateFields() {
        return !JTextField_NAME.getText().trim().isEmpty() &&
                !JTextField_STR.getText().trim().isEmpty() &&
                !JTextField_CT.getText().trim().isEmpty() &&
                !JTextField_ST.getText().trim().isEmpty() &&
                !JTextField_ZIP.getText().trim().isEmpty() &&
                !JTextField_CCNR.getText().trim().isEmpty() &&
                !JTextField_ExpDate.getText().trim().isEmpty();
    }

    @Override
    protected void JRadioButtonExtra_mouseClicked(MouseEvent event) {
        // Handle extra radio button click specific to JDialog_AddCCAccount
    }

    private void addEntryInTable(CreditCardAccount account) {
        System.out.println("rowcount " + parentframe.getModel().getRowCount());
        parentframe.getRowData()[0] = account.getAccountNumber();
        parentframe.getRowData()[1] = account.getCustomer().getName();
        parentframe.getRowData()[2] = account.getExpiryDate();
        parentframe.getRowData()[3] = account.getAccountType();
        parentframe.getRowData()[4] = account.getBalance();

        parentframe.getModel().addRow(parentframe.getRowData());
    }

    private CreditCardAccount createNewAccount() {
        Customer customer = new Customer(
                JTextField_NAME.getText(),
                "TODO:",
                "TODO:");
        customer.setAddress(createNewAddress());
        String accountNumber = JTextField_CCNR.getText();

        CreditCardAccount account = (CreditCardAccount) accountFactory.createAccount(AccountType.COMPANY, accountNumber, customer);
        account.setExpiryDate(JTextField_ExpDate.getText());
        if(JRadioButton_Chk.isSelected()){
            account.setStrategy(new GoldCardStrategy());
            System.out.println("Gold Set");
        } else if(JRadioButton_Sav.isSelected()){
            account.setStrategy(new SilverCardStrategy());
            System.out.println("Silver Set");
        }else if(JRadioButton_Extra.isSelected()){
            account.setStrategy(new BronzeCardStrategy());
            System.out.println("Bronze Set");
        }

        CreditCardForm cardFrm = (CreditCardForm) parentframe;
        cardFrm.getCreditCardAccountService().createAccount(account);
        return account;
    }

    private Address createNewAddress(){
        return new Address(JTextField_STR.getText(),JTextField_ST.getText(),JTextField_CT.getText(), JTextField_ZIP.getText());
    }
}
