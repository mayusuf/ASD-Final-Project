package org.lotusbank.ui.ccard;

import lombok.Getter;
import org.lotusbank.creditcard.service.CreditCardAccountService;
import org.lotusbank.creditcard.service.CreditCardAccountServiceImpl;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountType;
import org.lotusbank.ui.framework.BaseUI;
import org.lotusbank.ui.framework.JDialog_Transaction;
import org.lotusbank.ui.framework.Launcher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

/**
 * A basic JFC based application.
 */
public class CreditCardForm extends BaseUI {
    public JButton JButton_NewCCAccount = new JButton();
    public JButton JButton_GenBill = new JButton();

    @Getter
    public CreditCardAccountService creditCardAccountService;

    public CreditCardForm(JFrame launcher) {
        super(launcher);
        creditCardAccountService = CreditCardAccountServiceImpl.getInstance();
        populateFrame();

        Collection<Account> accounts = creditCardAccountService.getAllAccounts();
        populateTableData(accounts);
    }

    @Override
    protected void withdrawButtonAction(ActionEvent actionEvent) {
        super.withdrawButtonAction(actionEvent);
        Collection<Account> accounts = creditCardAccountService.getAllAccounts();
        populateTableData(accounts);
    }

    @Override
    protected void depositButtonAction(ActionEvent actionEvent) {
        super.depositButtonAction(actionEvent);
        Collection<Account> accounts = creditCardAccountService.getAllAccounts();
        populateTableData(accounts);
    }

    private void populateTableData(Collection<Account> accounts) {
        for (int i = 0; i < getModel().getRowCount(); ) {
            getModel().removeRow(0);
        }

        for (Account account: accounts){
            if (account.getAccountType()==AccountType.CREDIT){
                getRowData()[0] = account.getAccountNumber();
                getRowData()[1] = account.getCustomer().getName();
                getRowData()[2] = LocalDate.now().plusMonths(3);
                getRowData()[3] = account.getAccountType();
                getRowData()[4] = account.getBalance();
                getModel().addRow(getRowData());
            }

        }
    }


    @Override
    public void populateButtons() {
        JButton_NewCCAccount.setText("Add Credit-card account");
        JPanel1.add(JButton_NewCCAccount);
        JButton_NewCCAccount.setBounds(24, 20, 192, 33);
        JButton_GenBill.setText("Generate Monthly bills");
        JButton_GenBill.setActionCommand("jbutton");
        JPanel1.add(JButton_GenBill);
        JButton_GenBill.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Charge");
        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_Exit.setText("Back");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 31);
    }

    @Override
    public void populateTableColumns() {
        model.addColumn("Name");
        model.addColumn("CC number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
    }

    @Override
    public void setApplicationTitle() {
        setTitle("Credit-card processing Application.");
    }

    @Override
    protected JDialog_Transaction templateWithdrawMethod(String accnr) {
        return new JDialog_Charge(this, accnr);
    }

    @Override
    protected JDialog_Transaction templateDepositMethod(String accnr) {
        return new JDialog_Deposit(this, accnr);
    }

    @Override
    public void populateButtonActionListeners() {

        addButtonListener(JButton_Exit, this::exitButtonAction);
        addButtonListener(JButton_NewCCAccount, this::addCCAccountButtonAction);
        addButtonListener(JButton_GenBill, this::generateBillButtonAction);
        addButtonListener(JButton_Deposit, this::depositButtonAction);
        addButtonListener(JButton_Withdraw, this::withdrawButtonAction);
    }

    private void addCCAccountButtonAction(ActionEvent actionEvent) {
        JDialog_AddCreditCardAccount pac = new JDialog_AddCreditCardAccount(this);
        pac.pack();
        pac.setSize(300, 380);
        pac.setLocationRelativeTo(getParent());
        pac.setVisible(true);
    }

    private void generateBillButtonAction(ActionEvent actionEvent) {
        JDialog_GenerateBill pac = new JDialog_GenerateBill(this);
        pac.pack();
        pac.setSize(425, 350);
        pac.setLocationRelativeTo(getParent());
        pac.setVisible(true);
    }


    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    static public void main(String args[]) {
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            (new CreditCardForm(new Launcher())).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

}
