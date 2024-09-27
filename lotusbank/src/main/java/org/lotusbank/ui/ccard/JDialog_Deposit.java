package org.lotusbank.ui.ccard;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.ui.framework.BaseUI;
import org.lotusbank.ui.framework.JDialog_Transaction;

import java.awt.event.ActionEvent;
import java.util.Objects;

public class JDialog_Deposit extends JDialog_Transaction {

    public JDialog_Deposit(BaseUI parent, String aname) {
        super(parent, aname, "Pay Bills");
    }

    public void JButtonOK_actionPerformed(ActionEvent event) {

        CreditCardForm cardForm = (CreditCardForm) parentframe;
        int selectionIndex = cardForm.getTable().getSelectionModel().getMinSelectionIndex();
        if (selectionIndex >= 0) {
            String accountNumber = (String) cardForm.getModel().getValueAt(selectionIndex, 0);
            String amount = JTextField_AMT.getText();

            cardForm.getCreditCardAccountService().charge(accountNumber, Objects.isNull(amount) ? 0 : Double.parseDouble(amount) * (-1));

            Account account = cardForm.getCreditCardAccountService().getAccount(accountNumber);
            cardForm.getTable().setValueAt(account.getBalance(), selectionIndex, 4);
        }
        dispose();

    }

}