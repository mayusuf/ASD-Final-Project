package org.lotusbank.ui.bank;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.ui.framework.BaseUI;
import org.lotusbank.ui.framework.JDialog_Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class JDialog_Deposit extends JDialog_Transaction {

    JTextField JTextField_Deposit = new JTextField();

    public JDialog_Deposit(BaseUI parent, String aaccnr) {
        super(parent, aaccnr, "Deposit Money");
    }

    public void JButtonOK_actionPerformed(ActionEvent event) {
        BankForm bankFrm = (BankForm) parentframe;
        int selectionIndex = bankFrm.getTable().getSelectionModel().getMinSelectionIndex();
        if(selectionIndex >= 0){
            String accountNumber = (String)bankFrm.getModel().getValueAt(selectionIndex, 0);
            String amount = JTextField_AMT.getText();
            bankFrm.getBankService().deposit(accountNumber, Objects.isNull(amount) ? 0: Double.parseDouble(amount));
            Account account = bankFrm.getBankService().getAccount(accountNumber);
            bankFrm.getTable().setValueAt(account.getBalance(),selectionIndex, 5);
        }
        dispose();
    }

}