package org.lotusbank.ui.framework;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseUI extends JFrame {
    JFrame launcher;
    public BaseUI(JFrame launcher){
        this.launcher=launcher;
    }
    /****
     * init variables in the object
     ****/
    public String clientName;
    public String accountnr;
    public String street;
    public String city;
    public String zip;
    public String state;
    public String accountType;
    public String amountDeposit;
    public String expdate;
    public String ccnumber;
    public boolean newaccount;
    public JPanel JPanel1 = new JPanel();




    //ssss
    protected boolean newAccount;

    @Getter
    public DefaultTableModel model;
    @Getter
    public JTable table;
    public JScrollPane scrollPane;
    @Getter
    public Object[] rowData;

    public JButton JButton_Deposit = new JButton();
    public JButton JButton_Withdraw = new JButton();
    public JButton JButton_Exit = new JButton();

    protected void populateFrame() {
        //thisframe = this;

        setApplicationTitle();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(595, 350);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 575, 310);
		/*
		/Add five buttons on the pane
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        scrollPane = new JScrollPane();
        model = new DefaultTableModel();
        table = new JTable(model);

        populateTableColumns();

        rowData = new Object[model.getColumnCount() + 2];
        newaccount = false;


        JPanel1.add(scrollPane);
        scrollPane.setBounds(12, 92, 444, 160);
        scrollPane.getViewport().add(table);
        table.setBounds(0, 0, 420, 0);
//        rowdata = new Object[8];

        populateButtons();

        this.setLocationRelativeTo(null);

        populateButtonActionListeners();
    }

    public abstract void populateButtonActionListeners();

    public abstract void populateButtons();

    public abstract void populateTableColumns();

    public abstract void setApplicationTitle();

    protected void addButtonListener(JButton button, ActionListener actionListener) {
        button.addActionListener(actionListener);
    }


    protected void exitButtonAction(ActionEvent actionEvent) {
        /*try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        this.setVisible(false);
        launcher.setVisible(true);

    }
    protected void withdrawButtonAction(ActionEvent actionEvent) {
        int selection = table.getSelectionModel().getMinSelectionIndex();
        if (selection >=0) {
            String accnr = (String) model.getValueAt(selection, 0);
            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Transaction jDialogTransaction = templateWithdrawMethod(accnr);
            jDialogTransaction.pack();
            jDialogTransaction.setSize(275, 140);
            jDialogTransaction.setLocationRelativeTo(getParent());
            jDialogTransaction.setVisible(true);
        }
    }

    protected abstract JDialog_Transaction templateWithdrawMethod(String accnr);


    protected void depositButtonAction(ActionEvent actionEvent) {
        int selection = table.getSelectionModel().getMinSelectionIndex();
        if (selection >=0) {
            String accnr = (String) model.getValueAt(selection, 0);
            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Transaction jDialogTransaction = templateDepositMethod(accnr);
            jDialogTransaction.pack();
            jDialogTransaction.setSize(275, 140);
            jDialogTransaction.setLocationRelativeTo(getParent());
            jDialogTransaction.setVisible(true);
        }
    }
    protected abstract JDialog_Transaction templateDepositMethod(String accnr);


}
