package org.lotusbank.ui.ccard;

import org.lotusbank.framework.domain.Account;
import org.lotusbank.ui.framework.BaseUI;
import org.lotusbank.ui.framework.JDialog_Transaction;

import java.awt.event.ActionEvent;
import java.util.Objects;


public class JDialog_Charge extends JDialog_Transaction {

    public JDialog_Charge(BaseUI parent, String aname) {
        super(parent, aname, "Charge Account");
    }

    public void JButtonOK_actionPerformed(ActionEvent event) {
        CreditCardForm cardFrm = (CreditCardForm) parentframe;
        int selectionIndex = cardFrm.getTable().getSelectionModel().getMinSelectionIndex();
        if (selectionIndex >= 0) {
            String accountNumber = (String) cardFrm.getModel().getValueAt(selectionIndex, 0);
            String amount = JTextField_AMT.getText();
            cardFrm.getCreditCardAccountService().deposit(accountNumber, Objects.isNull(amount) ? 0 : Double.parseDouble(amount) * (-1));
            Account account = cardFrm.getCreditCardAccountService().getAccount(accountNumber);
            cardFrm.getTable().setValueAt(account.getBalance(), selectionIndex, 4);
        }
        dispose();
    }
}