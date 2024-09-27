package org.lotusbank.ui.bank;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.ui.framework.BaseUI;
import org.lotusbank.ui.framework.JDialog_Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;


public class JDialog_Withdraw extends JDialog_Transaction {


    public JDialog_Withdraw(BaseUI parent, String aaccnr) {
        super(parent, aaccnr, "Withdraw Money");
    }

    public void JButtonOK_actionPerformed(ActionEvent event) {
        BankForm bankFrm = (BankForm) parentframe;
        int selectionIndex = bankFrm.getTable().getSelectionModel().getMinSelectionIndex();
        if(selectionIndex >= 0){
            String accountNumber = (String)bankFrm.getModel().getValueAt(selectionIndex, 0);
            String amount = JTextField_AMT.getText();
            bankFrm.getBankService().withdraw(accountNumber, Objects.isNull(amount) ? 0: Double.parseDouble(amount));
            Account account = bankFrm.getBankService().getAccount(accountNumber);
            bankFrm.getTable().setValueAt(account.getBalance(),selectionIndex, 5);
        }
        dispose();
    }

}